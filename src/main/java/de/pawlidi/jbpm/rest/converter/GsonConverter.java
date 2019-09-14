/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.pawlidi.jbpm.rest.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 
 * @author PAWLIDIM
 *
 */
public final class GsonConverter extends Converter.Factory implements IConverter {

	private Gson gson;

	public GsonConverter() {
		super();
		this.gson = new Gson();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * retrofit2.Converter.Factory#responseBodyConverter(java.lang.reflect.Type,
	 * java.lang.annotation.Annotation[], retrofit2.Retrofit)
	 */
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		return new ResponseBodyConverter<>(gson.getAdapter(TypeToken.get(type)));
	}

	private class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
		private final TypeAdapter<T> adapter;

		private ResponseBodyConverter(TypeAdapter<T> adapter) {
			super();
			this.adapter = adapter;
		}

		@Override
		public T convert(ResponseBody value) throws IOException {
			JsonReader jsonReader = gson.newJsonReader(value.charStream());
			try {
				return adapter.read(jsonReader);
			} finally {
				value.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * retrofit2.Converter.Factory#requestBodyConverter(java.lang.reflect.Type,
	 * java.lang.annotation.Annotation[], java.lang.annotation.Annotation[],
	 * retrofit2.Retrofit)
	 */
	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
			Annotation[] methodAnnotations, Retrofit retrofit) {
		return new RequestBodyConverter<>(gson.getAdapter(TypeToken.get(type)));
	}

	private class RequestBodyConverter<T> implements Converter<T, RequestBody> {

		private final TypeAdapter<T> adapter;

		public RequestBodyConverter(TypeAdapter<T> adapter) {
			this.adapter = adapter;
		}

		@Override
		public RequestBody convert(T value) throws IOException {
			Buffer buffer = new Buffer();
			Writer writer = new OutputStreamWriter(buffer.outputStream(), Charset.forName(UTF_8));
			JsonWriter jsonWriter = gson.newJsonWriter(writer);
			adapter.write(jsonWriter, value);
			jsonWriter.close();
			return RequestBody.create(MediaType.parse(MEDIA_TYPE), buffer.readByteString());
		}

	}
}

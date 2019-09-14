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
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 
 * @author PAWLIDIM
 *
 */
public class PrimitiveConverter extends Converter.Factory implements IConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * retrofit2.Converter.Factory#responseBodyConverter(java.lang.reflect.Type,
	 * java.lang.annotation.Annotation[], retrofit2.Retrofit)
	 */
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		if (type.equals(String.class)) {
			return new Converter<ResponseBody, String>() {
				@Override
				public String convert(ResponseBody value) throws IOException {
					return value.string();
				}
			};
		} else if (type.equals(Integer.class)) {
			return new Converter<ResponseBody, Integer>() {
				@Override
				public Integer convert(ResponseBody value) throws IOException {
					return Integer.valueOf(value.string());
				}
			};
		} else if (type.equals(Double.class)) {
			return new Converter<ResponseBody, Double>() {
				@Override
				public Double convert(ResponseBody value) throws IOException {
					return Double.valueOf(value.string());
				}
			};
		} else if (type.equals(Float.class)) {
			return new Converter<ResponseBody, Float>() {
				@Override
				public Float convert(ResponseBody value) throws IOException {
					return Float.valueOf(value.string());
				}
			};
		} else if (type.equals(Boolean.class)) {
			return new Converter<ResponseBody, Boolean>() {
				@Override
				public Boolean convert(ResponseBody value) throws IOException {
					return Boolean.valueOf(value.string());
				}
			};
		}
		return super.responseBodyConverter(type, annotations, retrofit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see retrofit2.Converter.Factory#requestBodyConverter(java.lang.reflect.Type,
	 * java.lang.annotation.Annotation[], java.lang.annotation.Annotation[],
	 * retrofit2.Retrofit)
	 */
	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
			Annotation[] methodAnnotations, Retrofit retrofit) {
		if (type.equals(String.class)) {
			return new Converter<String, RequestBody>() {
				@Override
				public RequestBody convert(String value) throws IOException {
					return RequestBody.create(MediaType.parse(MEDIA_TYPE), value);
				}
			};
		} else if (type.equals(Integer.class)) {
			return new Converter<Integer, RequestBody>() {
				@Override
				public RequestBody convert(Integer value) throws IOException {
					String strValue = (value != null ? "" + value : null);
					return RequestBody.create(MediaType.parse(MEDIA_TYPE), "" + strValue);
				}
			};
		} else if (type.equals(Double.class)) {
			return new Converter<Double, RequestBody>() {
				@Override
				public RequestBody convert(Double value) throws IOException {
					String strValue = (value != null ? "" + value : null);
					return RequestBody.create(MediaType.parse(MEDIA_TYPE), "" + strValue);
				}
			};
		} else if (type.equals(Float.class)) {
			return new Converter<Float, RequestBody>() {
				@Override
				public RequestBody convert(Float value) throws IOException {
					String strValue = (value != null ? "" + value : null);
					return RequestBody.create(MediaType.parse(MEDIA_TYPE), "" + strValue);
				}
			};
		} else if (type.equals(Boolean.class)) {
			return new Converter<Boolean, RequestBody>() {
				@Override
				public RequestBody convert(Boolean value) throws IOException {
					String strValue = (value != null ? "" + value : null);
					return RequestBody.create(MediaType.parse(MEDIA_TYPE), "" + strValue);
				}
			};
		}
		return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
	}

}

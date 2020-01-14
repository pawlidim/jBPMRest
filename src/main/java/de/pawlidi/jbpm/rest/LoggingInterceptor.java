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
package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * @author PAWLIDIM
 *
 */
public class LoggingInterceptor implements Interceptor {

	private static final Logger log = Logger.getLogger(LoggingInterceptor.class.getName());

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();

		log.log(Level.FINE, String.format("Sending request %s", request.url()));

		Response response = chain.proceed(request);

		log.log(Level.FINE, String.format("Received response %s ", response.code()));
		return response;
	}

}

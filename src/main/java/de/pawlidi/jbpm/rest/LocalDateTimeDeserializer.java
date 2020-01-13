package de.pawlidi.jbpm.rest;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	public LocalDateTimeDeserializer() {
		this(null);
	}

	public LocalDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		Long dateTime = node.get("java.util.Date").asLong();

		return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.systemDefault());
	}

}

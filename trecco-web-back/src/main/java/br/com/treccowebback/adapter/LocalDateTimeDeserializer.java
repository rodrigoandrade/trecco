package br.com.treccowebback.adapter;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author rodrigo.andrade
 *
 */

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDate> {
	
	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		return LocalDate.parse(jsonParser.getText());
	}
}
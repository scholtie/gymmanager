package com.schol.gymmanager.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.schol.gymmanager.model.BusinessHours;

import java.io.IOException;

public class BusinessHoursSerializer extends JsonSerializer<BusinessHours> {
    @Override
    public void serialize(BusinessHours value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeObjectField("openTime", value.getOpenTime());
        gen.writeObjectField("closeTime", value.getCloseTime());
        gen.writeObjectField("day", value.getDay());
        gen.writeEndObject();
    }
}


package com.devcors.javaacademy.carlocation.jms;

import com.devcors.javaacademy.carlocation.data.dto.CarLocationDTO;
import com.devcors.javaacademy.carlocation.service.CarLocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CarLocationListener {

    private final ObjectMapper objectMapper;
    private final CarLocationService carLocationService;

    @RabbitListener(queues = "car-location-queue")
    public void listen(String message) {
        try {
            CarLocationDTO carLocationDTO = objectMapper.readValue(message, CarLocationDTO.class);
            carLocationService.saveNewCarLocation(carLocationDTO);
        } catch (JsonProcessingException e) {
            log.error("Error parsing message", e);
        }
    }
}

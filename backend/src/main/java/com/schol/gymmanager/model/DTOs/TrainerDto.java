package com.schol.gymmanager.model.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schol.gymmanager.model.Gender;
import lombok.Builder;
import lombok.Data;

import javax.sql.rowset.serial.SerialBlob;
import java.io.Serializable;

@Data
@Builder
public class TrainerDto implements Serializable {
    private final Long gymId;
    private final String email;
    private final String password;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String status;
    private String imgPath;
    private String introduction;
    private long rating;

    @JsonCreator
    public TrainerDto(
            @JsonProperty("gymId") Long gymId,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("introduction") String introduction,
            @JsonProperty("status") String status,
            @JsonProperty("imgPath") String imgPath,
            @JsonProperty("gender") String gender,
            @JsonProperty("rating") long rating,
            @JsonProperty("userName") String userName) {
        super();
        this.gymId = gymId;
        this.introduction = introduction;
        this.status = status;
        this.imgPath = imgPath;
        this.gender = gender;
        this.rating = rating;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
}

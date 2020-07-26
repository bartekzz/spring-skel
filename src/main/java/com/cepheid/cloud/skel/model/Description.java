package com.cepheid.cloud.skel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel
public class Description extends AbstractEntity
{
    @ApiModelProperty(notes = "Description of the description")
    private String description;

    public Description()
    {
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

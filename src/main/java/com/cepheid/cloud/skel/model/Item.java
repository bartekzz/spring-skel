package com.cepheid.cloud.skel.model;

import com.cepheid.cloud.skel.validator.group.ValidatedOnCreationOnly;
import com.cepheid.cloud.skel.validator.UniqueItemName;
import com.cepheid.cloud.skel.validator.UniqueItemState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "Items")
@ApiModel
public class Item extends AbstractEntity {

    @UniqueItemName(groups = ValidatedOnCreationOnly.class)
    @NotBlank(message = "Name is mandatory")
    @Column(unique=true)
    @ApiModelProperty(notes = "name of the item", required = true)
    private String name;
    @UniqueItemState(enumClass = State.class)
    @ApiModelProperty(notes = "state of the item", allowableValues = "UNDEFINED, VALID, INVALID")
    private String state;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "descriptions of the item")
    private List<Description> descriptions;

    public Item() {}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public List<Description> getDescriptions()
    {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions)
    {
        this.descriptions = descriptions;
    }
}

package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.hbsitescommons.commons.dto.EditableResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmmoDTO extends EditableResource {
    private UUID id;
    private String name;
    private Integer roundsShotWithEach;
}

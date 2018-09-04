package com.jurisdiction.inforeport.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegionSeparate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer groupid;
    private String name;
    private String imeino;
    private Integer sptts;
    /*private Integer bimeicount;*/

}

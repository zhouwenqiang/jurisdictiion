package com.jurisdiction.inforeport.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DetailsSeparate implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bindimei;
    private String promsisdn;
    private String brandname;
    private String tename;
    /*private Integer bimeicount;*/

}

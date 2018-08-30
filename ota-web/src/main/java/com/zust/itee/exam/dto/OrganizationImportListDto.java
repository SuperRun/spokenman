package com.zust.itee.exam.dto;

import java.util.List;

/**
 * Created by dxb on 2016/12/10.
 */
public class OrganizationImportListDto {

    List<OrganizationExcelDto> orgs;

    public List<OrganizationExcelDto> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrganizationExcelDto> orgs) {
        this.orgs = orgs;
    }
}

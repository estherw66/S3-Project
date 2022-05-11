package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}

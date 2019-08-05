package com.test.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User2 implements Serializable{
    private static final long serialVersionUID = 9085886691811169694L;
    private Integer id;

    private String username;

    private String password;
}
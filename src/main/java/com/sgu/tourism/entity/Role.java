package com.sgu.tourism.entity;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  private long id;
  private String roleName;
  private Set<Permissions> permissions;



}

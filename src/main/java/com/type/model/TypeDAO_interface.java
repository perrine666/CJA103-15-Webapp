package com.type.model;

import java.util.*;

public interface TypeDAO_interface {
          public void insert(TypeVO typeVO);
          public void update(TypeVO typeVO);
          public TypeVO findByPrimaryKey(Integer typeId);
          public List<TypeVO> getAll();

}

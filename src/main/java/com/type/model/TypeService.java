package com.type.model;

import java.util.List;

public class TypeService {

	private TypeDAO_interface dao;

	public TypeService() {
		dao = new TypeJDBCDAO();
	}

	public TypeVO addType(String typeName, String note) {

		TypeVO typeVO = new TypeVO();


		typeVO.setTypeName(typeName);
		typeVO.setNote(note);

		dao.insert(typeVO);

		return typeVO;
	}

	public TypeVO updateType(Integer typeId, String typeName, String note) {

		TypeVO typeVO = new TypeVO();
		
		typeVO.setTypeId(typeId);
		typeVO.setTypeName(typeName);
		typeVO.setNote(note);

		dao.update(typeVO);

		return typeVO;
	}



	public TypeVO getOneType(Integer typeId) {
		return dao.findByPrimaryKey(typeId);
	}

	public List<TypeVO> getAll() {
		return dao.getAll();
	}
}

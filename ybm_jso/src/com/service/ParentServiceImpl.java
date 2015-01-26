package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ParentDaoImpl;
import com.inter.ParentService;
import com.pojo.Parent;

@Service
public class ParentServiceImpl implements ParentService {

	@Resource
	private ParentDaoImpl parentdaoimpl;
	@Override
	public int Save(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		return parentdaoimpl.Save(parent);
	}

	@Override
	public int Del(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		return parentdaoimpl.Del(parent);
	}

	@Override
	public List<Parent> all_parent(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		return parentdaoimpl.all_parent(parent);
	}

	@Override
	public Parent one_parent(String idcard) throws Exception {
		// TODO Auto-generated method stub
		return parentdaoimpl.one_parent(idcard);
	}

	@Override
	public int DelMangParents(String idcards) throws Exception {
		// TODO Auto-generated method stub
		return parentdaoimpl.DelMangParents(idcards);
	}

}

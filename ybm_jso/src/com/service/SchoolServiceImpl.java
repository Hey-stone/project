package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.SchoolDaoImpl;
import com.inter.SchoolService;
import com.pojo.School;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Resource
	private SchoolDaoImpl schooldaoimpl;
	@Override
	public int Save(School school) throws Exception {
		// TODO Auto-generated method stub
		return schooldaoimpl.Save(school);
	}

	@Override
	public int Del(School school) throws Exception {
		// TODO Auto-generated method stub
		return schooldaoimpl.Del(school);
	}

	@Override
	public List<School> allschool(School school) throws Exception {
		// TODO Auto-generated method stub
		return schooldaoimpl.allschool(school);
	}

	@Override
	public School one_school(String idcard) throws Exception {
		// TODO Auto-generated method stub
		return schooldaoimpl.one_school(idcard);
	}

	@Override
	public int DelMangSchools(String idcards) throws Exception {
		// TODO Auto-generated method stub
		return schooldaoimpl.DelMangSchools(idcards);
	}

}

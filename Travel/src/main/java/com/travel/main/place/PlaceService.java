package com.travel.main.place;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
	
	private final PlaceMapper placeMapper;
	
	// select
	public List<Place> getTest() {
		return this.placeMapper.getAllTest();
	}
	
	// searchName
	public List<Place> searchByName(String placename) {
		return this.placeMapper.searchTestName(placename);
	}
	
	// searchCategory
	public List<Place> searchBycategory(String category) {
		return this.placeMapper.searchTestCategory(category);
	}
	
	// api json 파싱 후 객체에 저장
	public void insertApiForLocation(String arg1,String arg2,String arg3,Firsts f) {
		MyApiClient myApi = new MyApiClient();
		List<Place> resMol = myApi.callGooglePlacesApi(arg1, f);	
		List<Place> landMol = myApi.callGooglePlacesApi(arg2, f);
		List<Place> cafeMol = myApi.callGooglePlacesApi(arg3, f);
		List<Place> mList = new ArrayList<Place>();
		mList.addAll(resMol);
		mList.addAll(landMol);
		mList.addAll(cafeMol);
		
		for(int i=0;i<mList.size();i++) {
			this.placeMapper.insertTest(mList.get(i));
		}
	
	}
	
	public List<Place> selectMol() {
		return this.placeMapper.getAllTest();
	}
	
	public void deleteMol(int id) {
		this.placeMapper.delPlace(id);
	}
	
	public void deleteMolWithId(int id) {
		this.placeMapper.delPlaceWithId(id);
	}
	
	public void deleteAll() {
		this.placeMapper.delAll();
	}
}

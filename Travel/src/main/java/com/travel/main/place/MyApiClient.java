package com.travel.main.place;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyApiClient {

    public List<Place> callGooglePlacesApi(String cate, Firsts fst) {
    	
    	// RestApi 사용을 위해 RestTemplate
    	RestTemplate restTemplate = new RestTemplate();
    	
    	/* Http 요청에 header를 추가해주기 위해 사용
    	 * https://developers.google.com/maps/documentation/places/web-service/nearby-search?hl=ko
    	 * 위 페이지 기준 -H가 header에 set 해줘서 보내야하는 내용이다.
    	-H 'Content-Type: application/json' 
    	-H "X-Goog-Api-Key: API_KEY"
    	-H "X-Goog-FieldMask: places.displayName"
    	
    	*/
        HttpHeaders headers = new HttpHeaders();    
        
        // 나의 Api Key
        String key = "AIzaSyCyr_dvSRyZMncsqv9lUpOAQVQrF2lG4iU";
        
        // setContentType으로 받을 데이터의 타입을 지정해준다. 
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // header에 안넣어준 남은 것들을 넣어줌.
        headers.set("X-Goog-Api-Key", key);
        
        // places.*은 전체를 가져오란 뜻.
        headers.set("X-Goog-FieldMask", "places.displayName,places.primaryTypeDisplayName,places.shortFormattedAddress,places.location,places.photos");
        
        /*
         * includedTypes : 찾을 곳
         * excludedTypes : 찾을 곳에서 제외할 곳
         * maxResultCount : 뽑을 데이터(JSON)의 개수
         * regionCode : 주소 한글로 뽑기 위해 넣은 속성
         * latitude : 위도경도
         * longitude : 위도경도
         * radius : 내가 찍은 좌표에서 원하는 곳을 찾는 범위(m단위)
         * rankPreference : 내가 찾을 정보를 정렬할 순서 (현재 DISTANCE : 가까운 거리 기준)
         * */
        String requestBody = "{ "
        		+ "\"includedTypes\": [\""+cate+"\"], "
        		+ "\"maxResultCount\": 20, "
        		+ "\"regionCode\":\"KR\","
        		+ "\"locationRestriction\": { \"circle\": "
        		+ "{ \"center\": "
        		+ "{ \"latitude\": "+fst.getLat()+","
        		+ " \"longitude\": "+fst.getLng()+" },"
        		+ " \"radius\": 15000.0 } }, "
        		+ "\"rankPreference\": \"DISTANCE\" }";

        // 만들어진 header와 requestBody를 Entity로 합침.
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 합쳐진 Entity를 특정 주소로 restTemplate를 사용해서 요청한다.
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "https://places.googleapis.com/v1/places:searchNearby",
                requestEntity,
                String.class);
     
        // 받은 json 파일을 저장할 String 변수
        String responseBody = responseEntity.getBody();
        try {
			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(responseBody);
			JSONArray molArray = (JSONArray) jo.get("places");
					
			List<Place> mList = new ArrayList<Place>();		
			
			for (int i = 0; i < molArray.size(); i++) {
		        JSONObject place = (JSONObject) molArray.get(i);
		        // 장소의 이름 추출
		        JSONObject displayName = (JSONObject) place.get("displayName");
		        String placename = (String) displayName.get("text");
		        
		        
		        // 장소의 카테고리 추출
		        String category;
		        if(place.get("primaryTypeDisplayName") != null) {
		        	JSONObject types = (JSONObject) place.get("primaryTypeDisplayName");
		        	category = (String) types.get("text");
		        }else {
		        	category = " ";
		        }
		        
		        // 주소 정보 추출
		        String address;
		        if(place.get("shortFormattedAddress") != null) {
		        	address = (String) place.get("shortFormattedAddress");
		        }else {
		        	address = " ";
		        }
		        
	        
		        // 위치 정보 추출
		        JSONObject location = (JSONObject) place.get("location");
		        double latitude = (double) location.get("latitude");
		        double longitude = (double) location.get("longitude");
		        
		        // 이미지 정보 추출
		        String url1 = "https://places.googleapis.com/v1/";
		        String url2 = "/media?maxHeightPx=120&maxWidthPx=120&key=";
		        
		        String photoname = null;
		        if(place.get("photos") != null) {
		        	JSONArray photosArray = (JSONArray) place.get("photos");
		        	JSONObject photos = (JSONObject) photosArray.get(0);
		        	photoname = (String) photos.get("name");	        	
		        }
		        
		        String photoUrl = url1 + photoname + url2 + key;
		        
		        // Mol 객체 생성하여 정보 저장
		        Place mol = new Place();
		        mol.setF_id(fst.getId());
		        mol.setImage(photoUrl);
		        mol.setPlacename(placename);
		        mol.setCategory(category);
		        mol.setAddress(address);
		        mol.setLatitude(latitude);
		        mol.setLongitude(longitude);

		        // 생성된 Mol 객체를 리스트에 추가
		        
		        mList.add(mol);
		        
		    }
			
			return mList;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
        
    }
}
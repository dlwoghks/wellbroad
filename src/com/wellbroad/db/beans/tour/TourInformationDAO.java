package com.wellbroad.db.beans.tour;

import com.wellbroad.db.connection.DBConnectionManager;

public class TourInformationDAO {
//1.�����ϰ����ϴ� Ŭ���� ��ü ����
	private DBConnectionManager pool = null;
	
//2.������ (�����ϰ����ϴ� Ŭ������ü ����)
	public TourInformationDAO() {
		try {
			pool=DBConnectionManager.getInstance();
			System.out.println("pool = "+pool);
		} catch(Exception e) {
			System.out.println("DBConnectable error = "+e);
		}
	}
	
//3.TourNation ���� �޼ҵ�
	//3-0. TourNation �� ���� �� getTourNationCount
	//3-1. TourNation ������� �������� getTourNationList
	//3-2. TourNation ���̺� ���� �߰�(admin) insertTourNation
	//3-3. TourNation ���̺� ���� ����(admin) updateTourNation
	//3-4. TourNation ���̺� ���� ����(admin) deleteTourNation

//4.TourCity ���� �޼ҵ�
	//4-0. TourCity �� ���� �� getTourCityCount
	//4-1. TourCity ���ø�� �������� getTourCityList
	//4-2. TourCity ���̺� ���� �߰�(admin) insertTourCity
	//4-3. TourCity ���̺� ���� ����(admin) updateTourCity
	//4-4. TourCity ���̺� ���� ����(admin) deleteTourCity

//5.TourFood/Spot/Stay ���� �޼ҵ�
	//4-0. TourFood/Spot/Stay �� �� getTourInformationCount
	//4-1. TourFood/Spot/Stay ��� �������� getTourInformationList
	//4-2. TourFood/Spot/Stay ���̺� �߰�(admin) insertTourInformation
	//4-3. TourFood/Spot/Stay ���̺� ����(admin) updateTourInformation
	//4-4. TourFood/Spot/Stay ���̺� ����(admin) deleteTourInformation
}

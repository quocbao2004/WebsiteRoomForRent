//package com.javaweb.WebsiteRoomForRent.repository.custom.impl;
//
//import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
//import com.javaweb.WebsiteRoomForRent.repository.custom.BuildingRepositoryCustom;
//import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//@Repository
//public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<BuildingEntity> findAll(BuildingSearchRequests buildingSearchRequests) {
//        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
//        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
//        queryNormal(buildingSearchRequests, where);
//        querySpecial(buildingSearchRequests, where);
//        where.append(" GROUP BY b.id ");
//        sql.append(where);
//        return entityManager.createNativeQuery(sql.toString(), BuildingEntity.class).getResultList();
//    }
//
//    private void queryNormal(BuildingSearchRequests buildingSearchRequests, StringBuilder where) {
//        try {
//            Field[] fields = BuildingSearchRequests.class.getDeclaredFields();
//
//            for (Field item : fields) {
//                item.setAccessible(true);
//                String fieldName = item.getName();
//
//                if(!fieldName.startsWith("floorArea") && !fieldName.startsWith("rentPrice")
//                        && !fieldName.equals("type")) {
//                    Object value = item.get(buildingSearchRequests);
//
//                    if (value != null) {
//                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
//                            where.append(" AND b." + fieldName + " = " + value + " ");
//                        else
//                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void querySpecial(BuildingSearchRequests buildingSearchRequests, StringBuilder where) {
//        Float floorAreaTo = buildingSearchRequests.getFloorAreaTo();
//        Float floorAreaFrom = buildingSearchRequests.getFloorAreaFrom();
//
//        if(floorAreaTo != null) {
//            where.append(" AND b.floorarea <= " + floorAreaTo + " ");
//        }
//        if(floorAreaFrom != null) {
//            where.append(" AND b.floorarea >= " + floorAreaFrom + " ");
//        }
//
//        Float rentPriceTo = buildingSearchRequests.getRentPriceTo();
//        Float rentPriceFrom = buildingSearchRequests.getRentPriceFrom();
//
//        if(rentPriceTo != null) {
//            where.append(" AND b.rentprice <= " + rentPriceTo + " ");
//        }
//        if(rentPriceFrom != null) {
//            where.append(" AND b.rentprice >= " + rentPriceFrom + " ");
//        }
//
//        if(buildingSearchRequests.getType() != null && buildingSearchRequests.getType().size() > 0) {
//            where.append(" AND b.type IN (");
//            List<String> types = buildingSearchRequests.getType();
//            for(int i = 0; i < types.size(); ++i) {
//                where.append("'"+ types.get(i) + "'");
//                if(i != buildingSearchRequests.getType().size() - 1) where.append(",");
//            }
//            where.append(")");
//        }
//    }
//}

package com.javaweb.WebsiteRoomForRent.repository.custom.impl;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.repository.custom.BuildingRepositoryCustom;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequests buildingSearchRequests) {
        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNormal(buildingSearchRequests, where);
        querySpecial(buildingSearchRequests, where);
        where.append(" GROUP BY b.id ");
        sql.append(where);
        return entityManager.createNativeQuery(sql.toString(), BuildingEntity.class).getResultList();
    }

    private void queryNormal(BuildingSearchRequests buildingSearchRequests, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchRequests.class.getDeclaredFields();

            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();

                if (!fieldName.startsWith("floorArea") && !fieldName.startsWith("rentPrice")
                        && !fieldName.equals("type")) {
                    Object value = item.get(buildingSearchRequests);

                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
                            where.append(" AND b." + fieldName + " = " + value + " ");
                        else
                            where.append(" AND b." + fieldName + " ILIKE '%" + value + "%' ");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void querySpecial(BuildingSearchRequests buildingSearchRequests, StringBuilder where) {
        Float floorAreaTo = buildingSearchRequests.getFloorAreaTo();
        Float floorAreaFrom = buildingSearchRequests.getFloorAreaFrom();

        if (floorAreaTo != null) {
            where.append(" AND b.floorarea <= " + floorAreaTo + " ");
        }
        if (floorAreaFrom != null) {
            where.append(" AND b.floorarea >= " + floorAreaFrom + " ");
        }

        Float rentPriceTo = buildingSearchRequests.getRentPriceTo();
        Float rentPriceFrom = buildingSearchRequests.getRentPriceFrom();

        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo + " ");
        }
        if (rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom + " ");
        }

        if (buildingSearchRequests.getType() != null && buildingSearchRequests.getType().size() > 0) {
            where.append(" AND b.type IN (");
            List<String> types = buildingSearchRequests.getType();
            for (int i = 0; i < types.size(); ++i) {
                where.append("'" + types.get(i) + "'");
                if (i != buildingSearchRequests.getType().size() - 1) where.append(",");
            }
            where.append(")");
        }
    }
}
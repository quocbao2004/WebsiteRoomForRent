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

                if(!fieldName.startsWith("floorArea") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchRequests);

                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
                            where.append(" AND b." + fieldName + " = " + value + " ");
                        else
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void querySpecial(BuildingSearchRequests buildingSearchRequests, StringBuilder where) {
        Long floorAreaTo = buildingSearchRequests.getFloorAreaTo();
        Long floorAreaFrom = buildingSearchRequests.getFloorAreaFrom();

        if(floorAreaTo != null) {
            where.append(" AND b.floorarea <= " + floorAreaTo + " ");
        }
        if(floorAreaFrom != null) {
            where.append(" AND b.floorarea >= " + floorAreaFrom + " ");
        }

        Long rentPriceTo = buildingSearchRequests.getRentPriceTo();
        Long rentPriceFrom = buildingSearchRequests.getRentPriceFrom();

        if(rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo + " ");
        }
        if(rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom + " ");
        }
    }
}

package com.javaweb.WebsiteRoomForRent.repository.custom.impl;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.repository.custom.BuildingRepositoryCustom;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(BuildingRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequests buildingSearchRequests) {
        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNormal(buildingSearchRequests, where);
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

                Object value = item.get(buildingSearchRequests);

                if (value != null) {
                    if(fieldName.compareTo("rentPrice") == 0) {
                        if (buildingSearchRequests.getRentPrice() == 0) continue;
                    }
                    if(fieldName.compareTo("floorArea") == 0) {
                        if(buildingSearchRequests.getFloorArea() == 0) continue;
                    }
                    if(fieldName.compareTo("id") == 0) {
                        where.append(" AND b." + fieldName + " = " + value + " ");
                        continue;
                    }
                    if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
                        where.append(" AND b." + fieldName + " <= " + value + " ");
                    else
                        where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

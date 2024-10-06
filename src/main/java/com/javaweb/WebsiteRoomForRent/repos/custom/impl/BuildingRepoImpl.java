package com.javaweb.WebsiteRoomForRent.repos.custom.impl;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.repos.custom.BuildingRepoCustom;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepoImpl implements BuildingRepoCustom {

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
                    if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
                        where.append(" AND b." + fieldName + " = " + value + " ");
                    else
                        where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

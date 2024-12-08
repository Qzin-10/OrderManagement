package com.Qzin.repository;


import com.Qzin.entity.ItemMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemMetaDataRepository extends JpaRepository<ItemMetaData,Integer>  {

    @Query("SELECT i FROM ItemMetaData i WHERE i.itemMetaDataUUID=:itemMetaDataUUID")
    Optional<ItemMetaData> getItemMetaDataByUUID(@Param("itemMetaDataUUID") String itemMetaDataUUID);

}



package com.ophid.gadgetmonitoringsystem.Repository;


import com.ophid.gadgetmonitoringsystem.Entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepo extends JpaRepository<Documents, Long> {



    //List<Documents> findDocBySearch(String id);

   //find one by product id

}

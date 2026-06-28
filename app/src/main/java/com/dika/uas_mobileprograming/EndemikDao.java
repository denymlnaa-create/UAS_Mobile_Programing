package com.dika.uas_mobileprograming;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EndemikDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEndemik(List<Endemik> endemikList);

    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    List<Endemik> getEndemikByType(String tipe);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%'")
    List<Endemik> searchEndemik(String query);

    @Query("SELECT COUNT(*) FROM endemik")
    int getEndemikCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorit(Favorit favorit);

    @Delete
    void deleteFavorit(Favorit favorit);

    @Query("SELECT * FROM favorit")
    List<Favorit> getAllFavorit();

    @Query("SELECT * FROM favorit WHERE id = :id LIMIT 1")
    Favorit getFavoritById(String id);
}

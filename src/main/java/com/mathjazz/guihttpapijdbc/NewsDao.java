package com.mathjazz.guihttpapijdbc;

import java.util.List;

public interface NewsDao {

    // CREATE
    void saveNews(long id, String text);
//
    //READ
    List<News> findAllNewses();

    // UPDATE
    void updateNews(long id, String text);

    // DELETE
    void deleteNews(long id);

    News findOneNews(long id);

}


package com.mathjazz.guihttpapijdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl implements NewsDao{

        private JdbcTemplate jdbcTemplate;

        @Autowired
        public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;

        }

        @Override
        public void saveNews(long id, String text) {
            News news = new News(id, text);
            String sql = "INSERT INTO newses VALUES(?, ?) ";
            jdbcTemplate.update(sql, news.getId(), news.getText());

        }

        @Override
        public List<News> findAllNewses() {
            List<News> newsList = new ArrayList<>();
            String sql = "SELECT * FROM newses";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            maps.stream().forEach(element -> newsList.add(new News(
                    Long.parseLong(String.valueOf(element.get("news_id"))),
                    String.valueOf(element.get("text"))
            )));
            return newsList;

        }

        @Override
        public void updateNews(long id, String text) {
          News news = new News(id, text);
            String sql = "UPDATE newses SET text = ? WHERE news_id  = ?";
          jdbcTemplate.update(sql, news.getText(),  news.getId());
//            jdbcTemplate.update(sql, text, id);
        }

        @Override
        public void deleteNews(long id) {

            String sql = "DELETE FROM newses WHERE news_id  = ?";
            jdbcTemplate.update(sql, id);

        }


//    // Lepsza opcja metody powyżej - wykorzystanie lambdy
//    @Override
//    public News getOne(long id) {
//        String sql = "SELECT * FROM Newss WHERE News_id  = ?";
//        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> new News(resultSet.getLong(1), resultSet.getString(2),resultSet.getString(3)), id);
//
//    }

        // Tutaj zamiast numerów kolumn podajemy nazwę kolumny - tak najlepiej!
        @Override
        public News findOneNews(long id) {
            String sql = "SELECT * FROM newses WHERE news_id  = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, i) -> new News(resultSet.getLong("news_id"), resultSet.getString("text")), id);

        }
}

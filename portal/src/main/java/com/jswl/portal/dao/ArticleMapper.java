package com.jswl.portal.dao;

import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int countByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int deleteByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int insert(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int insertSelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    List<Article> selectByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByPrimaryKey(Article record);
    
    List<Article> showArticleByColumnidLimit(@Param("columnId")Integer columnId,@Param("limitNum")Integer limitNum);
    
    List<Article> showArticleByLike(@Param("title")String title);
    
    Article showPreArticle(Integer articleId);
    
    Article showNextArticle(Integer articleId);
    
    List<ArticleDto> showListPage();
}
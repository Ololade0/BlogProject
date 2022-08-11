package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.data.repository.ArticleRepository;

import africa.semicolon.blogProject.dtos.requests.AddArticleRequest;

import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;
import africa.semicolon.blogProject.dtos.requests.ViewArticleRequest;

import africa.semicolon.blogProject.exceptions.ArticleExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ArticleServiceImpl implements ArticleServices {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentServices commentService;

    @Override
    public Article saveArticle(AddArticleRequest articleRequest) {
        Article article = new Article();
        article.setBody(articleRequest.getBody());
        article.setTitle(articleRequest.getTitle());
        return articleRepository.save(article);
    }

    @Override
    public Article viewArticle(ViewArticleRequest viewArticleRequest) {
        Article article = articleRepository.findArticleByTitle(viewArticleRequest.getTitle());
        if (article != null) {
            throw new ArticleExistsException(article + "does not exist");
        }
        return article;
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Article saveComment(AddCommentRequest addCommentRequest) {
        Comment comment = commentService.addComment(addCommentRequest);
        Article article = articleRepository.findArticleByTitle(addCommentRequest.getTitle());
        if (article.getBlogName() == addCommentRequest.getBlogName()) {
            article.getComments().add(comment);
          ;
        }
        return articleRepository.save(article);

    }

}












//    @Override
//    public List<AllContactResponse> findContactBelongingTo(String userEmail) {
//        User user = userRespository.findUserByEmailAddress(userEmail);
//        List<Contact>allUserContact = user.getContacts();
//        List<AllContactResponse> response = new ArrayList<>();
//        for(var contact : allUserContact){
//            AllContactResponse singleResponse = new AllContactResponse();
//            Mapper.map(contact, singleResponse);
//
//            response.add(singleResponse);
//        }
//        return response;
//    }






package mnstate.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mnstate.example.projectthree.api.ApiClient;
import mnstate.example.projectthree.api.ApiInterface;
import mnstate.example.projectthree.models.Article;
import mnstate.example.projectthree.models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class working extends AppCompatActivity {

    public static String API_KEY="31d1b6a6b909473eb6f86ddeae6f89e0";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles= new ArrayList<>();
    private Adapter adapter;
    private String TAG=working.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(working.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJson("");



    }

    public void LoadJson(final String keyword){
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        String country=Utils.getCountry();
        String language=Utils.getLanguage();

        Call<News> call;
        if(keyword.length()>0){
            call=apiInterface.getNewsSearch(keyword,language,"publishedAt",API_KEY);
        }else{
            call=apiInterface.getNews(country,API_KEY);

        }


        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if(response.isSuccessful() && response.body().getArticles()!=null){
                    if(!articles.isEmpty()){
                        articles.clear();
                    }
                    articles=response.body().getArticles();
                    adapter=new Adapter(articles,working.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    initListner();
                }
                else{
                    Toast.makeText(working.this,"No Result found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }


    private void initListner(){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(working.this,NewsDetailActivity.class);
                Article article=articles.get(position);
                intent.putExtra("url",article.getUrl());
                intent.putExtra("title",article.getTitle());
                intent.putExtra("img",article.getUrlToImage());
                intent.putExtra("date",article.getPublishedAt());
                intent.putExtra("source",article.getSource().getName());
                intent.putExtra("author",article.getAuthor());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.meny_main,menu);
        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView=(SearchView)menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem =menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search for Latest");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>2){
                    LoadJson(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LoadJson(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);

    return true;
    }
}

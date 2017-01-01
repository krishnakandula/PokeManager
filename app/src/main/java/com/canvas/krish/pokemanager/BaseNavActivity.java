package com.canvas.krish.pokemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.canvas.krish.pokemanager.pokemonlist.PokemonListActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by Krishna Chaitanya Kandula on 12/31/2016.
 */

public class BaseNavActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener{

    private final int POKEDEX_DRAWER_ITEM_IDENTIFIER = 0;
    private final int TEAMS_DRAWER_ITEM_IDENTIFIER = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupNavDrawer(Activity activity){
        PrimaryDrawerItem pokedexDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.nav_drawer_item_pokedex)
                .withIdentifier(POKEDEX_DRAWER_ITEM_IDENTIFIER);

        PrimaryDrawerItem teamsDrawerItem = new PrimaryDrawerItem()
                .withName(R.string.nav_drawer_item_teams)
                .withIdentifier(TEAMS_DRAWER_ITEM_IDENTIFIER);

        ProfileDrawerItem mainProfileItem = new ProfileDrawerItem().withName("Krishna C Kandula").withEmail("krishna.c.kandula@gmail.com");

        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(activity)
                .addProfiles(mainProfileItem)
                .build();

        Drawer drawer = new DrawerBuilder()
                .withActivity(activity)
                .withTranslucentNavigationBar(false)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        pokedexDrawerItem,
                        teamsDrawerItem
                )
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch (position) {
            case POKEDEX_DRAWER_ITEM_IDENTIFIER:
                Intent intent = new Intent(this, PokemonListActivity.class);
                startActivityIfNeeded(intent, Intent.FLAG_ACTIVITY_SINGLE_TOP);
                return true;
            case TEAMS_DRAWER_ITEM_IDENTIFIER:
                return true;
            default:
                return false;
        }
    }
}
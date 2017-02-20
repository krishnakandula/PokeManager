package com.canvas.krish.pokemanager.pokemondetail.viewpager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.canvas.krish.pokemanager.data.models.detail.Stat;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by krishnakandula on 1/23/17.
 */

public class InfoFragment extends Fragment implements InfoContract.View {

    private Unbinder mUnbinder;
    private InfoContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private static final String LOG_TAG = InfoFragment.class.getSimpleName();

    public static final String INFO_FRAGMENT_POKEMON_ID_KEY = "info_fragment_pokemon_id_key";
    @BindView(R.id.detail_info_stats_chart) HorizontalBarChart statsChart;
    @BindView(R.id.detail_info_description_textview) TextView descriptionTextView;
    @BindView(R.id.detail_info_type1_textview) TextView type1TextView;
    @BindView(R.id.detail_info_type2_textview) TextView type2TextView;

    public static InfoFragment newInstance(int pokemonId) {
        Bundle args = new Bundle();
        args.putInt(INFO_FRAGMENT_POKEMON_ID_KEY, pokemonId);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pokemonId = getArguments().getInt(INFO_FRAGMENT_POKEMON_ID_KEY);
        this.mPresenter = new InfoPresenter(this, pokemonId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail_info, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showPokemonInfo(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem) {
        descriptionTextView.setText(pokemonListItem.getDescription());
        //Set types
        type1TextView.setText(pokemonListItem.getType1());
        String type2;
        if((type2 = pokemonListItem.getType2()) != null)
            type2TextView.setText(type2);
        else
            type2TextView.setVisibility(View.GONE);

        showStatsGraph(pokemonDetail);
    }

    private void showStatsGraph(PokemonDetail pokemonDetail){
        setupBarChart();
        updateChartData(pokemonDetail.getStats());

        //Refresh chart
        statsChart.invalidate();
    }

    private void updateChartData(List<Stat> stats){
        List<BarEntry> entries = new ArrayList<>();

        int index = 0;
        for(Stat stat : stats)
            entries.add(new BarEntry(index++, stat.getBaseStat()));

        BarDataSet dataSet = new BarDataSet(entries, "Stats");
        dataSet.setColor(Color.WHITE);
        dataSet.setDrawValues(false);
//        dataSet.setValueTextColor(Color.WHITE);
//        dataSet.setValueTextSize(17f);

        BarData data = new BarData(dataSet);
        statsChart.setData(data);
        statsChart.invalidate();
    }

    /**
     * Set the stat chart basic properties
     */
    private void setupBarChart(){
        Description description = new Description();
        description.setText("");

        statsChart.setDescription(description);
        statsChart.getXAxis().setTextColor(Color.WHITE);
        statsChart.getXAxis().setValueFormatter(xAxisValueFormatter);
        statsChart.getXAxis().setDrawGridLines(false);
        statsChart.getXAxis().setDrawLabels(true);
        statsChart.getXAxis().setTextSize(13f);
        statsChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        statsChart.getAxisRight().setEnabled(false);
        statsChart.getAxisLeft().setTextColor(Color.WHITE);
        statsChart.getAxisLeft().setDrawGridLines(false);
        statsChart.getAxisLeft().setAxisMinimum(0);
        statsChart.getAxisLeft().setAxisMaximum(255);
        statsChart.getLegend().setEnabled(false);
        statsChart.setTouchEnabled(false);
        statsChart.setFitBars(true);
        statsChart.setHardwareAccelerationEnabled(true);
        //Make room for x axis labels on right
//        statsChart.setExtraRightOffset(25f);
//        statsChart.setExtraLeftOffset(10f);
    }

    private IAxisValueFormatter xAxisValueFormatter = new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int index = (int) value;
            switch (index){
                case 0:
                    return "Speed";
                case 1:
                    return "Sp. Def";
                case 2:
                    return "Sp. Atk";
                case 3:
                    return "Defense";
                case 4:
                    return "Attack";
                case 5:
                    return "HP";
                default:
                    return "";
            }
        }
    };

    @Override
    public void showLoadingIndicator() {
        Log.d(LOG_TAG, "loading indicator");
        String progressBarTitle = "Loading";
        String progressBarMessage = "Please wait...";
        boolean isCancelable = false;

        if(mProgressDialog == null)
            mProgressDialog = new ProgressDialog(getContext());

        mProgressDialog.setTitle(progressBarTitle);
        mProgressDialog.setMessage(progressBarMessage);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        if(mProgressDialog != null) {
            mProgressDialog.hide();
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}

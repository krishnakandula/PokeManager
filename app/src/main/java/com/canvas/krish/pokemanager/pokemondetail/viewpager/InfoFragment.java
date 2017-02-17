package com.canvas.krish.pokemanager.pokemondetail.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.canvas.krish.pokemanager.data.models.detail.Stat;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.wang.avi.AVLoadingIndicatorView;

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

    public static final String INFO_FRAGMENT_POKEMON_ID_KEY = "info_fragment_pokemon_id_key";
    @BindView(R.id.detail_info_stats_chart) HorizontalBarChart statsChart;
    @BindView(R.id.detail_info_loading_indicator) AVLoadingIndicatorView loadingIndicator;

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
        loadingIndicator.show();
        mPresenter.start();
    }

    @Override
    public void showPokemonInfo(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem) {
        loadingIndicator.smoothToHide();
        setupBarChart(pokemonDetail, pokemonListItem);
    }

    private void setupBarChart(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem){
        List<Stat> stats = pokemonDetail.getStats();
        List<BarEntry> entries = new ArrayList<>();

        int index = 0;
        for(Stat stat : stats)
            entries.add(new BarEntry(index++, stat.getBaseStat(), "Hi"));

        BarDataSet dataSet = new BarDataSet(entries, "Stats");
        dataSet.setColor(Color.WHITE);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(17f);

        BarData data = new BarData(dataSet);

        if(statsChart != null) {
            statsChart.setData(data);

            Description description = new Description();
            description.setText("");

            statsChart.setDescription(description);
            statsChart.getXAxis().setTextColor(Color.WHITE);
            statsChart.getXAxis().setValueFormatter(xAxisValueFormatter);
            statsChart.getXAxis().setDrawGridLines(false);
            statsChart.getXAxis().setDrawLabels(true);
            statsChart.getAxisRight().setEnabled(false);
            statsChart.getAxisLeft().setTextColor(Color.WHITE);
            statsChart.getAxisLeft().setDrawGridLines(false);
            statsChart.getAxisLeft().setAxisMinimum(0);
            statsChart.getAxisLeft().setAxisMaximum(150);
            statsChart.getLegend().setEnabled(false);
            statsChart.setTouchEnabled(false);
            statsChart.setFitBars(true);
            statsChart.setHardwareAccelerationEnabled(true);
            //Make room for x axis labels on right
            statsChart.setExtraRightOffset(30f);
            //Refresh chart
            statsChart.invalidate();
        }
    }

    private IAxisValueFormatter xAxisValueFormatter = new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int index = (int) value;
            switch (index){
                case 0:
                    return "HP";
                case 1:
                    return "Attack";
                case 2:
                    return "Defense";
                case 3:
                    return "Sp. Atk";
                case 4:
                    return "Sp. Def";
                case 5:
                    return "Speed";
                default:
                    return "";
            }
        }
    };

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}

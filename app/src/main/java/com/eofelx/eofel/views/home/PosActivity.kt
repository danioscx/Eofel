package com.eofelx.eofel.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.eofelx.eofel.R
import com.eofelx.eofel.adapters.Adapter
import com.eofelx.eofel.adapters.PosAdapter
import com.eofelx.eofel.databinding.ActivityPosBinding
import com.eofelx.eofel.models.BaseModel
import com.eofelx.eofel.models.UserModel
import com.eofelx.eofel.views.home.pos.*
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import java.util.*

class PosActivity : AppCompatActivity() {
    var binding: ActivityPosBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPosBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding!!.etalase.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    StoreFront::class.java
                )
            )
        }
        userView()
        discountView()
        voucherView()
        reportView()
        stockView()
        performView()
    }

    private fun userView() {
        binding!!.pelanggan.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    UserActivity::class.java
                )
            )
        }
        val models: MutableList<UserModel> = ArrayList()
        models.add(
            UserModel(
                R.raw.slider3,
                "Ahmad Ikhsan"
            )
        )
        models.add(
            UserModel(
                R.raw.slider2,
                "Upin ipin"
            )
        )
        models.add(
            UserModel(
                R.raw.slider1,
                "Tabuti"
            )
        )
        binding!!.recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerView.adapter = PosAdapter(models, object : Adapter.OnClickListener {
            override fun <T : BaseModel?> onClick(click: T) {}
        })
    }

    private fun discountView() {
        binding!!.diskon.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    DiscountActivity::class.java
                )
            )
        }
    }


    private fun voucherView() {
        binding!!.voucher.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext,
                    VoucherActivity::class.java
                )
            )
        }
    }

    private fun reportView() {
        binding!!.report.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext,
                    ReportActivity::class.java
                )
            )
        }
    }

    private fun stockView() {
        binding!!.stock.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    StockActivity::class.java
                )
            )
        }
    }

    private fun performView() {
        val maker = Maker(applicationContext, R.layout.perform_maker)
        binding!!.perform.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    PerformActivity::class.java
                )
            )
        }
        binding!!.lineChart.setPinchZoom(false)
        binding!!.lineChart.setDrawGridBackground(false)
        val values = ArrayList<Entry>()
        values.add(Entry(1F, 50F))
        values.add(Entry(2F, 39F))
        values.add(Entry(3F, 100F))
        values.add(Entry(4F, 10F))
        values.add(Entry(5F, 60F))
        val set1: LineDataSet
        if (binding!!.lineChart.data != null &&
            binding!!.lineChart.data.dataSetCount > 0
        ) {
            set1 = binding!!.lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            binding!!.lineChart.data.notifyDataChanged()
            binding!!.lineChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Sample Data")
            set1.setDrawIcons(false)
            set1.enableDashedLine(10f, 5f, 0f)
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            set1.color = Color.DKGRAY
            set1.setCircleColor(Color.DKGRAY)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 9f
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            if (Utils.getSDKInt() >= 18) {
                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_orange)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.DKGRAY
            }
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val data = LineData(dataSets)
            binding!!.lineChart.data = data
            maker.chartView = binding!!.lineChart
            binding!!.lineChart.invalidate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    @SuppressLint("ViewConstructor")
    internal class Maker(context: Context?, layoutResource: Int) :
        MarkerView(context, layoutResource) {

        var date: TextView = findViewById(R.id.date)

        @SuppressLint("SetTextI18n")
        override fun refreshContent(e: Entry, highlight: Highlight) {
            if (e is CandleEntry) {
                date.text = "" + Utils.formatNumber(e.high, 0, true)
            } else {
                date.text = "" + Utils.formatNumber(e.y, 0, true)
            }
            super.refreshContent(e, highlight)
        }

        override fun getOffset(): MPPointF {
            return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
        }

    }
}
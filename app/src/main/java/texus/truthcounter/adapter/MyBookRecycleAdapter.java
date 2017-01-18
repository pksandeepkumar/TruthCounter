package texus.truthcounter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import texus.truthcounter.R;
import texus.truthcounter.datamodels.TruthInfo;
import texus.truthcounter.db.DBHelper;
import texus.truthcounter.utility.DateManager;

/**
 * Created by sandeep on 18/01/2017
 */
public class MyBookRecycleAdapter extends RecyclerView.Adapter<MyBookRecycleAdapter.ViewHolder> {

    Context context;
    ArrayList<TruthInfo> truthInfos;

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View  mView;
        public final TextView  tvDate;
        public final TextView  tvDescription;
        public final ImageView  imImage;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate = ( TextView ) view.findViewById(R.id.textViewDate);
            tvDescription = ( TextView ) view.findViewById(R.id.textViewDescription);
            imImage = ( ImageView ) view.findViewById(R.id.imageViewGoodOrBad);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public TruthInfo getValueAt(int position) {
        return truthInfos.get(position);
    }

    public MyBookRecycleAdapter(Context context, ArrayList<TruthInfo> truthInfos) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.context = context;
        this.truthInfos = truthInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_list_item, parent, false);
        //view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        TruthInfo truthInfo = truthInfos.get(position);

        if(truthInfo != null) {

            String dateString = new DateManager(truthInfo.date).getDayNameDD_MM_YYY__HHMMAM();
            holder.tvDate.setText(dateString);
            if(truthInfo.value) {
                holder.imImage.setImageResource(R.drawable.ic_good);
            } else {
                holder.imImage.setImageResource(R.drawable.ic_bad);
            }
            truthInfo = DBHelper.getAGoodOrBadWithDescription(context, truthInfo.id);
            if(truthInfo != null && holder.tvDescription != null) {
                holder.tvDescription.setText(truthInfo.description);
            }
//			SetDescriptionTask task = new SetDescriptionTask(tvDescription, truthInfo.id);
//			task.execute();
        } else {
            Log.e("Adapeter","Images are NULL");
        }

    }

    @Override
    public int getItemCount() {
        if(truthInfos == null) return 0;
        return truthInfos.size();
    }
}
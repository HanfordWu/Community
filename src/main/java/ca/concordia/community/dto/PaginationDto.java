package ca.concordia.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-24 9:41 a.m.
 */
@Data
public class PaginationDto<T> {

    private List<T> data;

    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showEndPage;
    private int page;
    private List<Integer> pages = new ArrayList<>();
    private boolean showNext;
    private int totalPage;


    public void setPagination(Integer count,
                              Integer page,
                              Integer size) {
        totalPage = 0;
        if (count % size == 0 ){
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }

        if (page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0){
                pages.add(0,page - i);
            }
            if (page + i <= totalPage){
                pages.add(page+i);
            }
        }



        showPrevious = page != 1;
        showNext = !page.equals(count);
        showFirstPage = !pages.contains(1);

        showEndPage = !pages.contains(totalPage);

    }
}

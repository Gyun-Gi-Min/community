package com.koreait.community;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {


    @Override
    public void execute(Request request, AttributeContext attributeContext) {
        System.out.println(" -- Called MenuPreparer--excute method ---------");
        /*호출되는지 보기위해 적음*/
        List<String> menuList = new ArrayList<>();
        menuList.add("게임");
        menuList.add("연예인");
        menuList.add("뉴스");
        menuList.add("정치");
        attributeContext.putAttribute("menuList",new Attribute(menuList),true);

    }
}

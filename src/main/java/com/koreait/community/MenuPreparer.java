package com.koreait.community;

import com.koreait.community.model.SubMenuVo;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {


    @Autowired
    private CommonMapper mapper;

    @Override
    public void execute(Request request, AttributeContext attributeContext) {
        //System.out.println(" -- Called MenuPreparer--excute method ---------");
        /*호출되는지 보기위해 적음*/
        attributeContext.putAttribute(Const.MENU_LIST,
                new Attribute(mapper.selMenuCategoryList()),true);

        List<SubMenuVo> subMenuList = new ArrayList<>();
        subMenuList.add(new SubMenuVo("profile", "프로필"));
        subMenuList.add(new SubMenuVo("changepw", "비밀번호 변경"));

        attributeContext.putAttribute(Const.SUB_MENU_LIST,
                new Attribute(subMenuList), true);

    }
}

package tests;

import org.junit.jupiter.api.Test;
import java.io.File;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Unit2Tests {
//    ошибка - не находит символ у @Before
//    @Before
//    public void setBrowser(){
//        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
//    }

    @Test
        // Проверка добавления СНИЛС из Населения
    void uploadNaselenieTest() {
        // Открыть загрузчик
        open("http://172.17.65.231:8080/MdmServices/loadFiles.zul");
        // Нажать кнопку загрузки файла Население
        //*[@id="dW1Q50"]

        File file = new File("C:\\Users\\taya4\\IdeaProjects\\qa_guru_course_2\\src\\test\\resources\\naselenie.csv");
        $(byText("АИС \"Население\"")).parent().parent()
                .$(byName("file")).uploadFile(file);
        sleep(2000);
        // открыть профиль и проверить наличие СНИЛС из загруженного файла
        open("http://172.17.65.232:8080/unidata-backend/api/dit/citizen/mdm_id/a4baa2c8-bb0c-4a44-87a9-60d8e884dabb?filter_delsing=false");
        $("html").shouldHave(text("\"id_tp_cd\":\"1000015\",\"ref_num\":\"14030163188\""));
    }

    @Test
        // Проверка добавления ИНН из МФЦ3
    void mfc3Test() {
        // Открыть загрузчик МФЦ
        open("http://172.24.27.133:8080/jenkins/job/Public_scripts/job/%D0%AD%D0%BC%D1%83%D0%BB%D1%8F%D1%82%D0%BE%D1%80%20%D0%95%D0%A2%D0%9F/job/%D0%9E%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%BA%D0%B0%20%D0%BD%D0%B0%20%D0%BF%D1%80%D0%B5%D0%B4%D0%BF%D1%80%D0%BE%D0%B4/build?delay=0sec");

        // Авторизация
        $(byId("j_username")).sendKeys("username");
        $(byName("j_password")).sendKeys("password");
        $(byValue("Sign in")).click();

        // Вставить сообщение для источника МФЦ3 (по умолчанию)
        $(byXpath("//textarea")).setValue("Sci-fi").pressEnter();
        $(byId("yui-gen1-button")).click();

        sleep(2000);
        // открыть профиль и проверить наличие отчества ребенка из загруженного сообщения
        open("http://172.17.65.232:8080/unidata-backend/api/dit/citizen/mdm_id/a4baa2c8-bb0c-4a44-87a9-60d8e884dabb?filter_delsing=false");
        $("html").shouldHave(text("ЕФРЕМОВНА"));

    }

    @Test
        // Проверка добавления ребенка из МФЦ15
    void mfc15Test() {
        // Открыть загрузчик МФЦ
        open("http://172.24.27.133:8080/jenkins/job/Public_scripts/job/%D0%AD%D0%BC%D1%83%D0%BB%D1%8F%D1%82%D0%BE%D1%80%20%D0%95%D0%A2%D0%9F/job/%D0%9E%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%BA%D0%B0%20%D0%BD%D0%B0%20%D0%BF%D1%80%D0%B5%D0%B4%D0%BF%D1%80%D0%BE%D0%B4/build?delay=0sec");

        // Авторизация
        $(byId("j_username")).sendKeys("username");
        $(byName("j_password")).sendKeys("password");
        $(byValue("Sign in")).click();

        // Вставить сообщение для источника МФЦ15 выбором из меню
        $(byXpath("//*[@id=\"main-panel\"]/form/table/tbody[1]/tr[1]/td[3]/div/select/option[2]")).click();
        $(byXpath("//textarea")).setValue("Sci-fi").pressEnter();
        $(byId("yui-gen1-button")).click();

        sleep(2000);
        // открыть профиль и проверить наличие ИНН из загруженного сообщения
        open("http://172.17.65.232:8080/unidata-backend/api/dit/citizen/mdm_id/a4baa2c8-bb0c-4a44-87a9-60d8e884dabb?filter_delsing=false");
        $("html").shouldHave(text("\"id_tp_cd\":\"1000013\",\"ref_num\":\"507541725000\""));
    }

    @Test
        // Проверка нотификации Kibana
    void kibanaTest() {
        // Открыть Kibana
        open("http://172.17.65.190:5601/app/kibana#/discover?_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-24h,to:now))&_a=(columns:!(_source),filters:!(('$state':(store:appState),meta:(alias:!n,disabled:!f,index:c2d6ed00-3b4c-11ea-af1b-3da9a80ecfda,key:phaseID,negate:!f,params:(query:notify_elkEsia),type:phrase),query:(match:(phaseID:(query:notify_elkEsia,type:phrase))))),index:c2d6ed00-3b4c-11ea-af1b-3da9a80ecfda,interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc)))");
        sleep(10000);
        // Найти нотификацию по sso
        getFocusedElement().sendKeys("2020-08-02--23-21-22ssogeneric");
        $(byAttribute("class", "euiButton__text euiSuperUpdateButton__text")).click();
        // Проверить наличие записи по ОМС
        $("html").shouldHave(text("7085215338513366"));

        // Не закрывать браузер
        Configuration.holdBrowserOpen = true;
    }

    @Test
        // Проверка наличия введенного текста в поиске
    void yandexTest() {
        String searchText = "2020";
        // Открыть краткий Яндекс
        open("https://ya.ru/");

        getFocusedElement().sendKeys(searchText);
        $(byText(searchText)).shouldBe(visible);
    }
}

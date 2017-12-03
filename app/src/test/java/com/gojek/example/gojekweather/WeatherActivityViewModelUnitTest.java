package com.gojek.example.gojekweather;

import com.gojek.example.gojekweather.network.MockNetworkClient;
import com.gojek.example.gojekweather.utils.UnitTestLogger;
import com.gojek.example.gojekweather.weather.WeatherActivityViewModel;
import com.gojek.example.gojekweather.weather.WeatherModel;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

/**
 * Created by vivek on 03/12/17.
 */

public class WeatherActivityViewModelUnitTest {

    WeatherActivityViewModel weatherActivityViewModel;

    @Mock
    WeatherModel weatherModel;

    private MockWebServer server;

    @Before
    public void setup() throws IOException{
        MockitoAnnotations.initMocks(this);

        weatherActivityViewModel = new WeatherActivityViewModel(new UnitTestLogger());
        weatherActivityViewModel.setWeatherModel(weatherModel);

        server = new MockWebServer();
    }

    @Test
    public void test_initial_values_of_view_model(){
        Assert.assertEquals(true, weatherActivityViewModel.isShowLoading());
        Assert.assertEquals(false, weatherActivityViewModel.isShowNetworkError());
        Assert.assertEquals(false, weatherActivityViewModel.isShowWeather());
    }

    @Test
    public void test_isloadingset_on_load_forecast(){
        weatherActivityViewModel.loadWeatherForcast();
        Assert.assertEquals(true, weatherActivityViewModel.isShowLoading());
        Assert.assertEquals(false, weatherActivityViewModel.isShowNetworkError());
        Assert.assertEquals(false, weatherActivityViewModel.isShowWeather());
    }

    @Test
    public void test_is_getForecast_called(){
        weatherActivityViewModel.loadWeatherForcast();

        ArgumentCaptor<WeatherActivityViewModel.WeatherActivityViewModelInterface> parameterCaptor = ArgumentCaptor
                .forClass(WeatherActivityViewModel.WeatherActivityViewModelInterface.class);
        Mockito.verify(weatherModel).getForecast(parameterCaptor.capture());
    }


    @Test
    public void test_is_error_shown_for_404() throws IOException, InterruptedException{

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody("{}")
        );
        server.start();

        weatherModel = new WeatherModel(new UnitTestLogger(),
                MockNetworkClient.getWeatherService(server.getUrl("/").toString()));
        weatherActivityViewModel.setWeatherModel(weatherModel);
        weatherActivityViewModel.loadWeatherForcast();

        Thread.sleep(1000);

        Assert.assertEquals(false, weatherActivityViewModel.isShowLoading());
        Assert.assertEquals(true, weatherActivityViewModel.isShowNetworkError());
        Assert.assertEquals(false, weatherActivityViewModel.isShowWeather());

        server.shutdown();
    }

    @Test
    public void test_is_weather_shown_for_200() throws IOException, InterruptedException{

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"location\":{\"name\":\"Bangalore\",\"region\":\"Karnataka\",\"country\":\"India\",\"lat\":12.98,\"lon\":77.58,\"tz_id\":\"Asia/Kolkata\",\"localtime_epoch\":1512316968,\"localtime\":\"2017-12-03 21:32\"},\"current\":{\"last_updated_epoch\":1512315911,\"last_updated\":\"2017-12-03 21:15\",\"temp_c\":19.0,\"temp_f\":66.2,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":0.0,\"wind_kph\":0.0,\"wind_degree\":93,\"wind_dir\":\"E\",\"pressure_mb\":1018.0,\"pressure_in\":30.5,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":100,\"cloud\":50,\"feelslike_c\":19.0,\"feelslike_f\":66.2,\"vis_km\":6.0,\"vis_miles\":3.0},\"forecast\":{\"forecastday\":[{\"date\":\"2017-12-03\",\"date_epoch\":1512259200,\"day\":{\"maxtemp_c\":24.4,\"maxtemp_f\":75.9,\"mintemp_c\":18.7,\"mintemp_f\":65.7,\"avgtemp_c\":20.3,\"avgtemp_f\":68.6,\"maxwind_mph\":11.0,\"maxwind_kph\":17.6,\"totalprecip_mm\":0.0,\"totalprecip_in\":0.0,\"avgvis_km\":16.8,\"avgvis_miles\":10.0,\"avghumidity\":84.0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"uv\":8.8},\"astro\":{\"sunrise\":\"06:27 AM\",\"sunset\":\"05:52 PM\",\"moonrise\":\"05:48 PM\",\"moonset\":\"05:49 AM\"},\"hour\":[{\"time_epoch\":1512241200,\"time\":\"2017-12-03 00:00\",\"temp_c\":18.7,\"temp_f\":65.7,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.6,\"wind_kph\":15.5,\"wind_degree\":96,\"wind_dir\":\"E\",\"pressure_mb\":1015.0,\"pressure_in\":30.4,\"precip_mm\":0.2,\"precip_in\":0.01,\"humidity\":93,\"cloud\":83,\"feelslike_c\":18.7,\"feelslike_f\":65.7,\"windchill_c\":18.7,\"windchill_f\":65.7,\"heatindex_c\":18.7,\"heatindex_f\":65.7,\"dewpoint_c\":17.5,\"dewpoint_f\":63.5,\"will_it_rain\":0,\"chance_of_rain\":\"51\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":4.7,\"vis_miles\":2.0},{\"time_epoch\":1512244800,\"time\":\"2017-12-03 01:00\",\"temp_c\":18.5,\"temp_f\":65.3,\"is_day\":0,\"condition\":{\"text\":\"Light drizzle\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/266.png\",\"code\":1153},\"wind_mph\":9.4,\"wind_kph\":15.1,\"wind_degree\":97,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":93,\"cloud\":82,\"feelslike_c\":18.5,\"feelslike_f\":65.3,\"windchill_c\":18.5,\"windchill_f\":65.3,\"heatindex_c\":18.5,\"heatindex_f\":65.3,\"dewpoint_c\":17.2,\"dewpoint_f\":63.0,\"will_it_rain\":0,\"chance_of_rain\":\"25\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":7.3,\"vis_miles\":4.0},{\"time_epoch\":1512248400,\"time\":\"2017-12-03 02:00\",\"temp_c\":18.2,\"temp_f\":64.8,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":99,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":92,\"cloud\":81,\"feelslike_c\":18.2,\"feelslike_f\":64.8,\"windchill_c\":18.2,\"windchill_f\":64.8,\"heatindex_c\":18.2,\"heatindex_f\":64.8,\"dewpoint_c\":16.9,\"dewpoint_f\":62.4,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":10.0,\"vis_miles\":6.0},{\"time_epoch\":1512252000,\"time\":\"2017-12-03 03:00\",\"temp_c\":17.9,\"temp_f\":64.2,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":98,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":93,\"cloud\":81,\"feelslike_c\":17.9,\"feelslike_f\":64.2,\"windchill_c\":17.9,\"windchill_f\":64.2,\"heatindex_c\":17.9,\"heatindex_f\":64.2,\"dewpoint_c\":16.8,\"dewpoint_f\":62.2,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":10.0,\"vis_miles\":6.0},{\"time_epoch\":1512255600,\"time\":\"2017-12-03 04:00\",\"temp_c\":17.6,\"temp_f\":63.7,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":97,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":94,\"cloud\":82,\"feelslike_c\":17.6,\"feelslike_f\":63.7,\"windchill_c\":17.6,\"windchill_f\":63.7,\"heatindex_c\":17.6,\"heatindex_f\":63.7,\"dewpoint_c\":16.6,\"dewpoint_f\":61.9,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":10.0,\"vis_miles\":6.0},{\"time_epoch\":1512259200,\"time\":\"2017-12-03 05:00\",\"temp_c\":17.3,\"temp_f\":63.1,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":96,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":95,\"cloud\":82,\"feelslike_c\":17.3,\"feelslike_f\":63.1,\"windchill_c\":17.3,\"windchill_f\":63.1,\"heatindex_c\":17.3,\"heatindex_f\":63.1,\"dewpoint_c\":16.5,\"dewpoint_f\":61.7,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":10.0,\"vis_miles\":6.0},{\"time_epoch\":1512262800,\"time\":\"2017-12-03 06:00\",\"temp_c\":17.8,\"temp_f\":64.0,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":99,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":93,\"cloud\":79,\"feelslike_c\":17.8,\"feelslike_f\":64.0,\"windchill_c\":17.8,\"windchill_f\":64.0,\"heatindex_c\":17.8,\"heatindex_f\":64.0,\"dewpoint_c\":16.6,\"dewpoint_f\":61.9,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":13.3,\"vis_miles\":8.0},{\"time_epoch\":1512266400,\"time\":\"2017-12-03 07:00\",\"temp_c\":18.3,\"temp_f\":64.9,\"is_day\":1,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/119.png\",\"code\":1006},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":102,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":91,\"cloud\":76,\"feelslike_c\":18.3,\"feelslike_f\":64.9,\"windchill_c\":18.3,\"windchill_f\":64.9,\"heatindex_c\":18.3,\"heatindex_f\":64.9,\"dewpoint_c\":16.7,\"dewpoint_f\":62.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":16.7,\"vis_miles\":10.0},{\"time_epoch\":1512270000,\"time\":\"2017-12-03 08:00\",\"temp_c\":18.8,\"temp_f\":65.8,\"is_day\":1,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/119.png\",\"code\":1006},\"wind_mph\":11.0,\"wind_kph\":17.6,\"wind_degree\":106,\"wind_dir\":\"ESE\",\"pressure_mb\":1016.0,\"pressure_in\":30.5,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":88,\"cloud\":72,\"feelslike_c\":18.8,\"feelslike_f\":65.8,\"windchill_c\":18.8,\"windchill_f\":65.8,\"heatindex_c\":18.8,\"heatindex_f\":65.8,\"dewpoint_c\":16.8,\"dewpoint_f\":62.2,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":20.0,\"vis_miles\":12.0},{\"time_epoch\":1512273600,\"time\":\"2017-12-03 09:00\",\"temp_c\":19.7,\"temp_f\":67.5,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":107,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.5,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":85,\"cloud\":57,\"feelslike_c\":19.7,\"feelslike_f\":67.5,\"windchill_c\":19.7,\"windchill_f\":67.5,\"heatindex_c\":20.7,\"heatindex_f\":69.3,\"dewpoint_c\":17.0,\"dewpoint_f\":62.6,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":20.0,\"vis_miles\":12.0},{\"time_epoch\":1512277200,\"time\":\"2017-12-03 10:00\",\"temp_c\":20.5,\"temp_f\":68.9,\"is_day\":1,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/119.png\",\"code\":1006},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":108,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.5,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":81,\"cloud\":41,\"feelslike_c\":20.5,\"feelslike_f\":68.9,\"windchill_c\":20.5,\"windchill_f\":68.9,\"heatindex_c\":22.5,\"heatindex_f\":72.5,\"dewpoint_c\":17.1,\"dewpoint_f\":62.8,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":20.0,\"vis_miles\":12.0},{\"time_epoch\":1512280800,\"time\":\"2017-12-03 11:00\",\"temp_c\":21.4,\"temp_f\":70.5,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":110,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":77,\"cloud\":26,\"feelslike_c\":21.4,\"feelslike_f\":70.5,\"windchill_c\":21.4,\"windchill_f\":70.5,\"heatindex_c\":24.4,\"heatindex_f\":75.9,\"dewpoint_c\":17.3,\"dewpoint_f\":63.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":20.0,\"vis_miles\":12.0},{\"time_epoch\":1512284400,\"time\":\"2017-12-03 12:00\",\"temp_c\":22.4,\"temp_f\":72.3,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.1,\"wind_kph\":16.2,\"wind_degree\":109,\"wind_dir\":\"ESE\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":73,\"cloud\":30,\"feelslike_c\":24.9,\"feelslike_f\":76.8,\"windchill_c\":22.4,\"windchill_f\":72.3,\"heatindex_c\":24.9,\"heatindex_f\":76.8,\"dewpoint_c\":17.3,\"dewpoint_f\":63.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":19.5,\"vis_miles\":12.0},{\"time_epoch\":1512288000,\"time\":\"2017-12-03 13:00\",\"temp_c\":23.4,\"temp_f\":74.1,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.1,\"wind_kph\":16.2,\"wind_degree\":109,\"wind_dir\":\"ESE\",\"pressure_mb\":1013.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":69,\"cloud\":33,\"feelslike_c\":25.4,\"feelslike_f\":77.7,\"windchill_c\":23.4,\"windchill_f\":74.1,\"heatindex_c\":25.4,\"heatindex_f\":77.7,\"dewpoint_c\":17.4,\"dewpoint_f\":63.3,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":19.0,\"vis_miles\":11.0},{\"time_epoch\":1512291600,\"time\":\"2017-12-03 14:00\",\"temp_c\":24.4,\"temp_f\":75.9,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":109,\"wind_dir\":\"ESE\",\"pressure_mb\":1012.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":65,\"cloud\":37,\"feelslike_c\":25.9,\"feelslike_f\":78.6,\"windchill_c\":24.4,\"windchill_f\":75.9,\"heatindex_c\":25.9,\"heatindex_f\":78.6,\"dewpoint_c\":17.4,\"dewpoint_f\":63.3,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.5,\"vis_miles\":11.0},{\"time_epoch\":1512295200,\"time\":\"2017-12-03 15:00\",\"temp_c\":23.9,\"temp_f\":75.0,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":9.4,\"wind_kph\":15.1,\"wind_degree\":103,\"wind_dir\":\"ESE\",\"pressure_mb\":1012.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":69,\"cloud\":40,\"feelslike_c\":25.6,\"feelslike_f\":78.1,\"windchill_c\":23.9,\"windchill_f\":75.0,\"heatindex_c\":25.6,\"heatindex_f\":78.1,\"dewpoint_c\":17.7,\"dewpoint_f\":63.9,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.4,\"vis_miles\":11.0},{\"time_epoch\":1512298800,\"time\":\"2017-12-03 16:00\",\"temp_c\":23.4,\"temp_f\":74.1,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":96,\"wind_dir\":\"E\",\"pressure_mb\":1012.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":72,\"cloud\":43,\"feelslike_c\":25.3,\"feelslike_f\":77.5,\"windchill_c\":23.4,\"windchill_f\":74.1,\"heatindex_c\":25.3,\"heatindex_f\":77.5,\"dewpoint_c\":18.1,\"dewpoint_f\":64.6,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.4,\"vis_miles\":11.0},{\"time_epoch\":1512302400,\"time\":\"2017-12-03 17:00\",\"temp_c\":22.9,\"temp_f\":73.2,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":8.7,\"wind_kph\":14.0,\"wind_degree\":90,\"wind_dir\":\"E\",\"pressure_mb\":1012.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":76,\"cloud\":46,\"feelslike_c\":25.0,\"feelslike_f\":77.0,\"windchill_c\":22.9,\"windchill_f\":73.2,\"heatindex_c\":25.0,\"heatindex_f\":77.0,\"dewpoint_c\":18.4,\"dewpoint_f\":65.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.3,\"vis_miles\":11.0},{\"time_epoch\":1512306000,\"time\":\"2017-12-03 18:00\",\"temp_c\":22.0,\"temp_f\":71.6,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":8.5,\"wind_kph\":13.7,\"wind_degree\":93,\"wind_dir\":\"E\",\"pressure_mb\":1013.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":80,\"cloud\":48,\"feelslike_c\":23.4,\"feelslike_f\":74.1,\"windchill_c\":22.0,\"windchill_f\":71.6,\"heatindex_c\":23.4,\"heatindex_f\":74.1,\"dewpoint_c\":18.4,\"dewpoint_f\":65.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.6,\"vis_miles\":11.0},{\"time_epoch\":1512309600,\"time\":\"2017-12-03 19:00\",\"temp_c\":21.1,\"temp_f\":70.0,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":8.5,\"wind_kph\":13.7,\"wind_degree\":96,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":85,\"cloud\":49,\"feelslike_c\":21.1,\"feelslike_f\":70.0,\"windchill_c\":21.1,\"windchill_f\":70.0,\"heatindex_c\":21.8,\"heatindex_f\":71.2,\"dewpoint_c\":18.4,\"dewpoint_f\":65.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.9,\"vis_miles\":11.0},{\"time_epoch\":1512313200,\"time\":\"2017-12-03 20:00\",\"temp_c\":20.2,\"temp_f\":68.4,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":8.3,\"wind_kph\":13.3,\"wind_degree\":98,\"wind_dir\":\"E\",\"pressure_mb\":1014.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":90,\"cloud\":51,\"feelslike_c\":20.2,\"feelslike_f\":68.4,\"windchill_c\":20.2,\"windchill_f\":68.4,\"heatindex_c\":20.2,\"heatindex_f\":68.4,\"dewpoint_c\":18.4,\"dewpoint_f\":65.1,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":19.2,\"vis_miles\":11.0},{\"time_epoch\":1512316800,\"time\":\"2017-12-03 21:00\",\"temp_c\":19.9,\"temp_f\":67.8,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":8.3,\"wind_kph\":13.3,\"wind_degree\":101,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":91,\"cloud\":61,\"feelslike_c\":19.9,\"feelslike_f\":67.8,\"windchill_c\":19.9,\"windchill_f\":67.8,\"heatindex_c\":19.9,\"heatindex_f\":67.8,\"dewpoint_c\":18.3,\"dewpoint_f\":64.9,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":19.0,\"vis_miles\":11.0},{\"time_epoch\":1512320400,\"time\":\"2017-12-03 22:00\",\"temp_c\":19.6,\"temp_f\":67.3,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":8.5,\"wind_kph\":13.7,\"wind_degree\":104,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":91,\"cloud\":72,\"feelslike_c\":19.6,\"feelslike_f\":67.3,\"windchill_c\":19.6,\"windchill_f\":67.3,\"heatindex_c\":19.6,\"heatindex_f\":67.3,\"dewpoint_c\":18.1,\"dewpoint_f\":64.6,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.7,\"vis_miles\":11.0},{\"time_epoch\":1512324000,\"time\":\"2017-12-03 23:00\",\"temp_c\":19.3,\"temp_f\":66.7,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.apixu.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":8.5,\"wind_kph\":13.7,\"wind_degree\":106,\"wind_dir\":\"ESE\",\"pressure_mb\":1015.0,\"pressure_in\":30.5,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":92,\"cloud\":82,\"feelslike_c\":19.3,\"feelslike_f\":66.7,\"windchill_c\":19.3,\"windchill_f\":66.7,\"heatindex_c\":19.3,\"heatindex_f\":66.7,\"dewpoint_c\":18.0,\"dewpoint_f\":64.4,\"will_it_rain\":0,\"chance_of_rain\":\"0\",\"will_it_snow\":0,\"chance_of_snow\":\"0\",\"vis_km\":18.5,\"vis_miles\":11.0}]}]}}")
        );
        server.start();

        weatherModel = new WeatherModel(new UnitTestLogger(),
                MockNetworkClient.getWeatherService(server.getUrl("/").toString()));
        weatherActivityViewModel.setWeatherModel(weatherModel);
        weatherActivityViewModel.loadWeatherForcast();

        Thread.sleep(1000);

        Assert.assertEquals(false, weatherActivityViewModel.isShowLoading());
        Assert.assertEquals(false, weatherActivityViewModel.isShowNetworkError());
        Assert.assertEquals(true, weatherActivityViewModel.isShowWeather());

        server.shutdown();
    }


}

package application.beerbeer.ResponsePack;

import java.util.List;

/**
 * Created by KOMPUTOR on 2016-05-16.
 */
public class Response {

    /**
     * id : 8
     * pub : Marynka
     * piwo : Pacific
     * link : www
     * progress : 16
     */

    private List<BeersBean> beers;
    /**
     * id : 2
     * pub : Marynka
     * link : http://beerparse.esy.es/pub.bmp
     */

    private List<PubsBean> pubs;

    public List<BeersBean> getBeers() {
        return beers;
    }

    public void setBeers(List<BeersBean> beers) {
        this.beers = beers;
    }

    public List<PubsBean> getPubs() {
        return pubs;
    }

    public void setPubs(List<PubsBean> pubs) {
        this.pubs = pubs;
    }

    public static class BeersBean {
        private String id;
        private String pub;
        private String piwo;
        private String link;
        private String progress;
        private String halfprice;
        private String threeprice;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPub() {
            return pub;
        }

        public void setPub(String pub) {
            this.pub = pub;
        }

        public String getPiwo() {
            return piwo;
        }

        public void setPiwo(String piwo) {
            this.piwo = piwo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getHalfprice() {
            return halfprice;
        }

        public void setHalfprice(String halfprice) {
            this.halfprice = halfprice;
        }

        public String getThreeprice() {
            return threeprice;
        }

        public void setThreeprice(String threeprice) {
            this.threeprice = threeprice;
        }
    }

    public static class PubsBean {
        private String id;
        private String pub;
        private String link;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPub() {
            return pub;
        }

        public void setPub(String pub) {
            this.pub = pub;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
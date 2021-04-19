(ns medic.view
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :refer [form-to text-area submit-button text-field drop-down]]
            [hiccup.element :refer [link-to]]))

(defn header2  
  "Header of pages"
  [text]
  (html5 
		  [:head
		   [:meta {:charset "utf-8"}]
		   [:meta
		    {:content "width=device-width, initial-scale=1", :name "viewport"}]
		   [:link {:href "img/apple-icon.png", :rel "apple-touch-icon"}]
		   [:link
		    {:href "img/favicon.ico",
		     :type "image/x-icon",
		     :rel "shortcut icon"}]
		   [:link {:href "css/bootstrap.min.css", :rel "stylesheet"}]
		   [:link {:href "css/templatemo.css", :rel "stylesheet"}]
		   [:link {:href "css/custom.css", :rel "stylesheet"}]
		   "<!-- Load fonts style after rendering the layout styles -->"
		   [:link
		    {:href
		     "https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap",
		     :rel "stylesheet"}]
		   [:link
		    {:href "css/fontawesome.min.css", :rel "stylesheet"}]
     [:script {:src "js/jquery-1.11.0.min.js"}]
  [:script {:src "js/jquery-migrate-1.2.1.min.js"}]
  [:script {:src "js/bootstrap.bundle.min.js"}]
  [:script {:src "js/templatemo.js"}]
  [:script
   {:src "https://canvasjs.com/assets/script/canvasjs.min.js"}]
  [:script {:src "js/custom.js"}]]
    [:body text]))

(defn score 
  "Score page"
  [text]
	(header2 
   [:div
    [:nav#templatemo_nav_top.navbar.navbar-expand-lg.bg-dark.navbar-light.d-none.d-lg-block
  [:div.container.text-light
   [:div.w-100.d-flex.justify-content-between
    [:div
     [:i.fa.fa-envelope.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "mailto:info@company.com"}
      "info@company.com"]
     [:i.fa.fa-phone.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "tel:010-020-0340"}
      "010-020-0340"]]
    [:div
     [:a.text-light
      {:rel "sponsored",
       :target "_blank",
       :href "https://fb.com/"}
      [:i.fab.fa-facebook-f.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.instagram.com/"}
      [:i.fab.fa-instagram.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://twitter.com/"}
      [:i.fab.fa-twitter.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.linkedin.com/"}
      [:i.fab.fa-linkedin.fa-sm.fa-fw]]]]]]
  [:nav.navbar.navbar-expand-lg.navbar-light.shadow
   [:div.container.d-flex.justify-content-between.align-items-center
    [:a.navbar-brand.text-success.logo.h1.align-self-center
     {:href "index.html"}
     "\n                Medic\n            "]
    [:button.navbar-toggler.border-0
     {:aria-label "Toggle navigation",
      :aria-expanded "false",
      :aria-controls "navbarSupportedContent",
      :data-bs-target "#templatemo_main_nav",
      :data-bs-toggle "collapse",
      :type "button"}
     [:span.navbar-toggler-icon]]
    [:div#templatemo_main_nav.align-self-center.collapse.navbar-collapse.flex-fill.d-lg-flex.justify-content-lg-between
     [:div.flex-fill
      [:ul.nav.navbar-nav.d-flex.justify-content-between.mx-lg-auto
       [:li.nav-item  (link-to {:class "nav-link"} "/index" "Home")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "About")]
       [:li.nav-item  (link-to {:class "nav-link"} "/search" "Search")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "Contact")]]]
     [:div.navbar.align-self-center.d-flex
      [:div.d-lg-none.flex-sm-fill.mt-3.mb-4.col-7.col-sm-auto.pr-3
       [:div.input-group
        [:input#inputMobileSearch.form-control
         {:placeholder "Search ...", :type "text"}]
        [:div.input-group-text [:i.fa.fa-fw.fa-search]]]]
      [:a.nav-icon.d-none.d-lg-inline
       {:data-bs-target "#templatemo_search",
        :data-bs-toggle "modal",
        :href "#"}
       [:i.fa.fa-fw.fa-search.text-dark.mr-2]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-cart-arrow-down.text-dark.mr-1]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "7"]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-user.text-dark.mr-3]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "+99"]]]]]]
  [:div#templatemo_search.modal.fade.bg-white
   {:aria-hidden "true",
    :aria-labelledby "exampleModalLabel",
    :role "dialog",
    :tabindex "-1"}
   [:div.modal-dialog.modal-lg
    {:role "document"}
    [:div.w-100.pt-1.mb-5.text-right
     [:button.btn-close
      {:aria-label "Close", :data-bs-dismiss "modal", :type "button"}]]
    [:form.modal-content.modal-body.border-0.p-0
     {:method "get", :action ""}
     [:div.input-group.mb-2
      [:input#inputModalSearch.form-control
       {:placeholder "Search ...", :name "q", :type "text"}]
      [:button.input-group-text.bg-success.text-light
       {:type "submit"}
       [:i.fa.fa-fw.fa-search.text-white]]]]]]
  [:div.skills
    [:ul.lines
     [:li.line.l--25
      [:span.line__label "\n        25%\n      "]]
     [:li.line.l--50 [:span.line__label "\n        50%\n      "]]
     [:li.line.l--75 [:span.line__label "\n        75%\n      "]]
     [:li.line.l--100 [:span.line__label "\n        100%\n      "]]]
    [:div.charts
     [:div.chart.chart--dev
      [:ul.chart--horiz
       (for [x (range 1 10)] 
         [:div [:br]
       [:li.chart__bar
        {:style (str "width: " (* (Double/parseDouble (nth (vals (get-in text [:Diseases x])) 0)) 100) "%;")}]
        [:span.chart__label (keys (get-in text [:Diseases x]))]
        [:br]])]]]]]))

(defn index [text]
  (header2 
    [:div
    [:nav#templatemo_nav_top.navbar.navbar-expand-lg.bg-dark.navbar-light.d-none.d-lg-block
  [:div.container.text-light
   [:div.w-100.d-flex.justify-content-between
    [:div
     [:i.fa.fa-envelope.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "mailto:info@company.com"}
      "info@company.com"]
     [:i.fa.fa-phone.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "tel:010-020-0340"}
      "010-020-0340"]]
    [:div
     [:a.text-light
      {:rel "sponsored",
       :target "_blank",
       :href "https://fb.com/"}
      [:i.fab.fa-facebook-f.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.instagram.com/"}
      [:i.fab.fa-instagram.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://twitter.com/"}
      [:i.fab.fa-twitter.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.linkedin.com/"}
      [:i.fab.fa-linkedin.fa-sm.fa-fw]]]]]]
  [:nav.navbar.navbar-expand-lg.navbar-light.shadow
   [:div.container.d-flex.justify-content-between.align-items-center
    [:a.navbar-brand.text-success.logo.h1.align-self-center
     {:href "index.html"}
     "\n                Medic\n            "]
    [:button.navbar-toggler.border-0
     {:aria-label "Toggle navigation",
      :aria-expanded "false",
      :aria-controls "navbarSupportedContent",
      :data-bs-target "#templatemo_main_nav",
      :data-bs-toggle "collapse",
      :type "button"}
     [:span.navbar-toggler-icon]]
    [:div#templatemo_main_nav.align-self-center.collapse.navbar-collapse.flex-fill.d-lg-flex.justify-content-lg-between
     [:div.flex-fill
      [:ul.nav.navbar-nav.d-flex.justify-content-between.mx-lg-auto
       [:li.nav-item  (link-to {:class "nav-link"} "/index" "Home")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "About")]
       [:li.nav-item  (link-to {:class "nav-link"} "/search" "Search")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "Contact")]]]
     [:div.navbar.align-self-center.d-flex
      [:div.d-lg-none.flex-sm-fill.mt-3.mb-4.col-7.col-sm-auto.pr-3
       [:div.input-group
        [:input#inputMobileSearch.form-control
         {:placeholder "Search ...", :type "text"}]
        [:div.input-group-text [:i.fa.fa-fw.fa-search]]]]
      [:a.nav-icon.d-none.d-lg-inline
       {:data-bs-target "#templatemo_search",
        :data-bs-toggle "modal",
        :href "#"}
       [:i.fa.fa-fw.fa-search.text-dark.mr-2]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-cart-arrow-down.text-dark.mr-1]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "7"]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-user.text-dark.mr-3]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "+99"]]]]]]
  [:div#templatemo_search.modal.fade.bg-white
   {:aria-hidden "true",
    :aria-labelledby "exampleModalLabel",
    :role "dialog",
    :tabindex "-1"}
   [:div.modal-dialog.modal-lg
    {:role "document"}
    [:div.w-100.pt-1.mb-5.text-right
     [:button.btn-close
      {:aria-label "Close", :data-bs-dismiss "modal", :type "button"}]]
    [:form.modal-content.modal-body.border-0.p-0
     {:method "get", :action ""}
     [:div.input-group.mb-2
      [:input#inputModalSearch.form-control
       {:placeholder "Search ...", :name "q", :type "text"}]
      [:button.input-group-text.bg-success.text-light
       {:type "submit"}
       [:i.fa.fa-fw.fa-search.text-white]]]]]]
  [:div#template-mo-zay-hero-carousel.carousel.slide
   {:data-bs-ride "carousel"}
   [:ol.carousel-indicators
    [:li.active
     {:data-bs-slide-to "0",
      :data-bs-target "#template-mo-zay-hero-carousel"}]
    [:li
     {:data-bs-slide-to "1",
      :data-bs-target "#template-mo-zay-hero-carousel"}]
    [:li
     {:data-bs-slide-to "2",
      :data-bs-target "#template-mo-zay-hero-carousel"}]]
   [:div.carousel-inner
    [:div.carousel-item.active
     [:div.container
      [:div.row.p-5
       [:div.mx-auto.col-md-8.col-lg-6.order-lg-last
        [:img.img-fluid
         {:alt "", :src (get-in (:articles text) [0 :urlToImage])}]]
       [:div.col-lg-6.mb-0.d-flex.align-items-center
        [:div.text-align-left.align-self-center
         [:h1.h1.text-success "TOP NEWS!" ]
         [:h3.h2 (get-in (:articles text) [0 :title])]
         [:p (get-in (:articles text) [0 :desc])]]]]]]
    [:div.carousel-item
     [:div.container
      [:div.row.p-5
       [:div.mx-auto.col-md-8.col-lg-6.order-lg-last
         [:img.img-fluid
          {:alt "", :src (get-in (:articles text) [1 :urlToImage])}]]
       [:div.col-lg-6.mb-0.d-flex.align-items-center
        [:div.text-align-left
         [:h1.h1 "TOP NEWS!"]
         [:h3.h2 (get-in (:articles text) [1 :title]) ]
         [:p (get-in (:articles text) [1 :desc])]]]]]]
    [:div.carousel-item
     [:div.container
      [:div.row.p-5
       [:div.mx-auto.col-md-8.col-lg-6.order-lg-last
        [:img.img-fluid
         {:alt "", :src (get-in (:articles text) [2 :urlToImage])}]]
       [:div.col-lg-6.mb-0.d-flex.align-items-center
        [:div.text-align-left
         [:h1.h1 "TOP NEWS!"]
         [:h3.h2 (get-in (:articles text) [2 :title])]
         [:p (get-in (:articles text) [2 :desc])]]]]]]]
   [:a.carousel-control-prev.text-decoration-none.w-auto.ps-3
    {:data-bs-slide "prev",
     :role "button",
     :href "#template-mo-zay-hero-carousel"}
    [:i.fas.fa-chevron-left]]
   [:a.carousel-control-next.text-decoration-none.w-auto.pe-3
    {:data-bs-slide "next",
     :role "button",
     :href "#template-mo-zay-hero-carousel"}
    [:i.fas.fa-chevron-right]]]]))

(defn search 
  "Search page"
  [par message]
	(header2
   [:div
   [:nav#templatemo_nav_top.navbar.navbar-expand-lg.bg-dark.navbar-light.d-none.d-lg-block
  [:div.container.text-light
   [:div.w-100.d-flex.justify-content-between
    [:div
     [:i.fa.fa-envelope.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "mailto:info@company.com"}
      "info@company.com"]
     [:i.fa.fa-phone.mx-2]
     [:a.navbar-sm-brand.text-light.text-decoration-none
      {:href "tel:010-020-0340"}
      "010-020-0340"]]
    [:div
     [:a.text-light
      {:rel "sponsored",
       :target "_blank",
       :href "https://fb.com/"}
      [:i.fab.fa-facebook-f.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.instagram.com/"}
      [:i.fab.fa-instagram.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://twitter.com/"}
      [:i.fab.fa-twitter.fa-sm.fa-fw.me-2]]
     [:a.text-light
      {:target "_blank", :href "https://www.linkedin.com/"}
      [:i.fab.fa-linkedin.fa-sm.fa-fw]]]]]]
  [:nav.navbar.navbar-expand-lg.navbar-light.shadow
   [:div.container.d-flex.justify-content-between.align-items-center
    [:a.navbar-brand.text-success.logo.h1.align-self-center
     {:href "index.html"}
     "\n                Medic\n            "]
    [:button.navbar-toggler.border-0
     {:aria-label "Toggle navigation",
      :aria-expanded "false",
      :aria-controls "navbarSupportedContent",
      :data-bs-target "#templatemo_main_nav",
      :data-bs-toggle "collapse",
      :type "button"}
     [:span.navbar-toggler-icon]]
    [:div#templatemo_main_nav.align-self-center.collapse.navbar-collapse.flex-fill.d-lg-flex.justify-content-lg-between
     [:div.flex-fill
      [:ul.nav.navbar-nav.d-flex.justify-content-between.mx-lg-auto
       [:li.nav-item  (link-to {:class "nav-link"} "/index" "Home")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "About")]
       [:li.nav-item  (link-to {:class "nav-link"} "/search" "Search")]
       [:li.nav-item (link-to {:class "nav-link"} "/index" "Contact")]]]
     [:div.navbar.align-self-center.d-flex
      [:div.d-lg-none.flex-sm-fill.mt-3.mb-4.col-7.col-sm-auto.pr-3
       [:div.input-group
        [:input#inputMobileSearch.form-control
         {:placeholder "Search ...", :type "text"}]
        [:div.input-group-text [:i.fa.fa-fw.fa-search]]]]
      [:a.nav-icon.d-none.d-lg-inline
       {:data-bs-target "#templatemo_search",
        :data-bs-toggle "modal",
        :href "#"}
       [:i.fa.fa-fw.fa-search.text-dark.mr-2]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-cart-arrow-down.text-dark.mr-1]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "7"]]
      [:a.nav-icon.position-relative.text-decoration-none
       {:href "#"}
       [:i.fa.fa-fw.fa-user.text-dark.mr-3]
       [:span.position-absolute.top-0.left-100.translate-middle.badge.rounded-pill.bg-light.text-dark
        "+99"]]]]]]
  [:div#templatemo_search.modal.fade.bg-white
   {:aria-hidden "true",
    :aria-labelledby "exampleModalLabel",
    :role "dialog",
    :tabindex "-1"}
   [:div.modal-dialog.modal-lg
    {:role "document"}
    [:div.w-100.pt-1.mb-5.text-right
     [:button.btn-close
      {:aria-label "Close", :data-bs-dismiss "modal", :type "button"}]]
    [:form.modal-content.modal-body.border-0.p-0
     {:method "get", :action ""}
     [:div.input-group.mb-2
      [:input#inputModalSearch.form-control
       {:placeholder "Search ...", :name "q", :type "text"}]
      [:button.input-group-text.bg-success.text-light
       {:type "submit"}
       [:i.fa.fa-fw.fa-search.text-white]]]]]]
  [:div {:class "form"}
		(form-to [:post "/search"]
         [:p {:style "color: #59ab6e;"} message]
         (text-field {:id "form-search" :placeholder "Type answare..."} "content")
         [:div]
         (drop-down :drop (map :text par) "selected")
         (submit-button {:class "btn btn-success" :id "sub1"} "Add symphtom"))
  [:div {:id "form2"}
    (form-to [:post "/score"]
            (submit-button   {:id "search-menu":class "btn btn-success"}  "Analyze"))]]]))

  
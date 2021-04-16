(ns medic.view
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :refer [form-to text-area submit-button text-field drop-down]]
            [hiccup.element :refer [link-to]]))

(defn header 
  "Header of pages"
  [text]
  (html5 [:head
          [:title "Medic"]"\t" 
					[:meta {:charset "UTF-8"}]"\t" 
         [:body
         text]]))

(defn index 
  "Index page"
  [par]
	(header  
   [:div
		(form-to [:post "/search"]
         (text-field {:id "form-search" :placeholder "Type answare..."} "content")
         [:div]
         (get-in par [1 :text])
         (drop-down :text par "selected")
         (submit-button {:class "buttons"} "Add symphtom"))
    (link-to {:id "search-menu":class "nav-link"} "/score" "Analyze")]))

(defn score 
  "Score page"
  [text]
	(header text))
  
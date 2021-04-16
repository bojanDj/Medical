(ns medic.view
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :refer [form-to text-area submit-button text-field drop-down]]))

(defn header 
  "Header of pages"
  [text]
  (html5 [:head
          [:title "Medic"]"\t" 
					[:meta {:charset "UTF-8"}]"\t" 
         [:body
         text]]))

(defn index 
  "Index page news"
  [par]
	(header  
		(form-to [:post "/search"]
         (text-field {:id "form-search" :placeholder "Type answare..."} "content")
         [:div]
         (get-in par [1 :text])
         (drop-down "text" par)
         (submit-button {:class "buttons"} "Add symphtom"))))
  
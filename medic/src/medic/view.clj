(ns medic.view
  (:require [hiccup.page :refer [html5]]))

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
  [text]
	(header  
		[:p text]))
  
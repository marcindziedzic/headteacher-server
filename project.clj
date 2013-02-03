(defproject headteacher-server "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Small App Facilitating Language Learning"
  :url "http://marcindziedzic.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
				 [ibdknox/clojurescript "0.0-1534"]
         [ring "1.1.8"]
	       [ring/ring-json "0.1.2"] ; replace with library really used
         [compojure "1.1.5"]
				 [enlive "1.0.1"]
         [enfocus "1.0.0-beta2"]
         [domina "1.0.1"]
         [fetch "0.1.0-alpha2"] ; replace with clojurescript ajax
         [liberator "0.8.0"]
         [com.taoensso/carmine "1.2.1"]]
  :source-paths ["src/clojure"]
  :resource-paths ["resources"]
  :jar-name "headteacher-server.jar"
  :uberjar-name "headteacher-server-standalone.jar"
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]

  :main headteacher.server

  :plugins [[lein-ring "0.7.5"]
            [lein-cljsbuild "0.2.10"]]

  :ring {:handler headteacher.server/handler
         :auto-refresh? false}

  :test-selectors {:default (fn [m] (not (or (:integration m) (:system m))))
                   :integration :integration
                   :system :system}

  :profiles {
              :dev { :test-paths ["test/clojure"]
                     :dependencies [[clj-webdriver "0.6.0-beta2"]
                                    [expectations "1.4.24"]
                                    [junit "4.8.1"]]
					 :plugins [[lein-autoexpect "0.2.5"]
							       [lein-expectations "0.0.7"]]
                     :cljsbuild
                     {:builds
                      [{:source-path "src/clojurescript",
                        :compiler
                        {:pretty-print true,
                         :output-to "resources/public/js/app.js",
                         :optimizations :simple}}]}}

              :production { :mirrors {"central" "http://s3pository.herokuapp.com/maven-central" }
                            :cljsbuild
                            {:builds
                             [{:source-path "src/clojurescript",
                               :compiler
                               {:pretty-print false,
                                :output-to "resources/public/js/app.js",
                                :optimizations :advanced}}]}}
              }

  )

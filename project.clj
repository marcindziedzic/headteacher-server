(defproject headteacher-server "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Small App Facilitating Language Learning"
  :url "http://marcindziedzic.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
				 [ibdknox/clojurescript "0.0-1534"]
				 [noir "1.3.0-beta10"]
				 [enlive "1.0.1"]
				 [domina "1.0.1"]]
  :source-paths ["src/clojure"]
  :test-paths ["test/clojure"]
  :resource-paths ["resources"]
  :compile-path "target/classes"
  :target-path "target/"
  :jar-name "headteacher-server.jar"
  :uberjar-name "headteacher-server-standalone.jar"
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]

  :plugins [[lein-ring "0.7.5"]
            [lein-cljsbuild "0.2.10"]]

  :ring {:handler headteacher.dev-server/handler}

  :cljsbuild  {
    :source-path "src/clojurescript"
    :compiler
    {
      :output-to "resources/public/js/app.js"
      :optimizations :simple
      :pretty-print true
      }})
(defproject postman "0.1.0-SNAPSHOT"
  :description "Postman is a clojure SMTP server"
  :url "http://github.com/iamedu/postman"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [io.netty/netty-all "4.0.9.Final"]
                 [org.clojure/tools.logging "0.2.6"]
                 [ch.qos.logback/logback-core "1.0.13"]
                 [ch.qos.logback/logback-classic "1.0.13"]
                 [org.clojure/tools.cli "0.2.4"]
                 [org.javassist/javassist "3.18.0-GA"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :aot [postman.handler]
  :main postman.core
  :profiles {:uberjar {:aot :all}})

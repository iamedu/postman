(ns postman.server
  (:require [clojure.tools.logging :as log])
  (:import [java.net InetSocketAddress]
           [io.netty.bootstrap ServerBootstrap]
           [io.netty.channel.nio NioEventLoopGroup]
           [io.netty.channel.socket.nio NioServerSocketChannel]
           [postman.handler PostmanHandlerInitializer]))

(defn start-server [^String hostname port & handlers]
  (let [bootstrap (.. (ServerBootstrap.)
                      (group (NioEventLoopGroup.))
                      (channel NioServerSocketChannel)
                      (localAddress (InetSocketAddress. hostname port))
                      (childHandler (PostmanHandlerInitializer.)))
        channel (.. bootstrap (bind) (sync) (channel))]
    (log/info "Server has been started on" hostname "port" port)
    (fn []
      (.close channel))))


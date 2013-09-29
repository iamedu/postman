(ns postman.handler
  (:require [clojure.tools.logging :as log])
  (:import [io.netty.buffer Unpooled]
           [io.netty.handler.codec.string StringDecoder]
           [io.netty.handler.codec DelimiterBasedFrameDecoder Delimiters]
           [io.netty.channel ChannelInboundHandlerAdapter ChannelHandlerContext]
           [io.netty.util CharsetUtil])
  (:gen-class
    :name postman.handler.PostmanHandlerInitializer
    :extends io.netty.channel.ChannelInitializer))

(defn make-frame-decoder []
  (DelimiterBasedFrameDecoder. 1000 (Delimiters/lineDelimiter)))

(defn make-smtp-handler []
  (let [state (atom {})]
    (proxy [ChannelInboundHandlerAdapter] []
      (channelRead [^ChannelHandlerContext ctx msg]
        (log/info msg)
        (.writeAndFlush (.channel ctx) msg)))))

(defn -initChannel [this ch]
  (doto (.pipeline ch)
    ;; (.addLast "frameDecoder" (make-frame-decoder))
    ;; ; We assume everything is encoded correctly
    ;; (.addLast "stringDecoder" (StringDecoder. (CharsetUtil/US_ASCII)))
    (.addLast "handler" (make-smtp-handler))))


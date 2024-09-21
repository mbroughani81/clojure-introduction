(ns io.github.mbroughani81.3-record-and-protocol.proto)

(defprotocol Seller-Proto
  (add-item [this item]))

(defprotocol Buyer-Proto
  (can-buy? [this item])
  (add-expense [this x])
  (charge-account [this x]))
